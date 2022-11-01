package com.qxdzbc.pcr.screen.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.R
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.screen.common.*
import com.qxdzbc.pcr.screen.main_screen.action.MainScreenAction
import com.qxdzbc.pcr.screen.main_screen.date_filter_view.DateType
import com.qxdzbc.pcr.screen.main_screen.date_filter_view.DateView
import com.qxdzbc.pcr.screen.main_screen.date_filter_view.MDatePickerDialog
import com.qxdzbc.pcr.screen.main_screen.entry_view.EntryView
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.ui.theme.PCRTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    state: MainScreenState,
    action: MainScreenAction,
    toEntryCreateScreen: () -> Unit,
    toManageTagScreen: () -> Unit,
    toFrontScreen: () -> Unit,
    executionScope: CoroutineScope = rememberCoroutineScope()
) {
    val scaffoldState = rememberScaffoldState()
    val userId: String? = state.userSt.value?.uid
    var isFromDatePickerOpen: Boolean by remember { ms(false) }
    var isToDatePickerOpen: Boolean by remember { ms(false) }
    val localCrScope = rememberCoroutineScope()
    var isTagSelectionDialogOpen:Boolean by remember { ms(false) }
    var selectedTagsForFilter:List<Tag> by remember { ms(emptyList()) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            PCRTopAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MIconButton(
                        imageVector = Icons.Default.Menu,
                        onClick = {
                            localCrScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        },
                        modifier = Modifier
                    )
                    MBox(
                        modifier = Modifier
                            .background(MaterialTheme.colors.surface)
                            .weight(1.0f)
                            .padding(horizontal = 10.dp)
                    ) {
                        BasicTextField(
                            value = state.mainScreenFilter.text ?: "", onValueChange = {
                                val newFilter = if (it.isEmpty()) {
                                    state.mainScreenFilter.copy(text = null)
                                } else {
                                    state.mainScreenFilter.copy(text = it)
                                }
                                action.filter(newFilter)
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
                            textStyle = TextStyle.Default.copy(color=MaterialTheme.colors.onSurface)
                        )
                    }
                    MIconButton(
                        painter = painterResource(id = R.drawable.sell_24),
                        onClick = {
                            isTagSelectionDialogOpen = true
                        }
                    )
                }
            }
        },
        drawerContent = {
            UserInfo(state.userSt.value ?: FirebaseUserWrapper.forPreview)
            ClickableDrawerItem("Manage tags") {
                localCrScope.launch {
                    scaffoldState.drawerState.close()
                }
                toManageTagScreen()
            }
            DrawerItem {
                ThemeSwitcher(isDark = state.isDark, switchTheme = {
                    localCrScope.launch {
                        scaffoldState.drawerState.close()
                        action.switchTheme(it)
                    }
                })
            }
            ClickableDrawerItem("Logout") {
                localCrScope.launch {
                    scaffoldState.drawerState.close()
                }
                action.logout()
                toFrontScreen()
            }
        },
        floatingActionButton = {
            MFloatButton(
                onClick = toEntryCreateScreen
            )
        }
    ) { contentPadding ->
        MBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .height(30.dp),
                    elevation = 10.dp
                ) {
                    MBox(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        DateView(
                            state.mainScreenFilter,
                            fromDateClick = {
                                isFromDatePickerOpen = true
                            },
                            toDateClick = {
                                isToDatePickerOpen = true
                            },
                            modifier = Modifier
                                .align(Alignment.Center),
                            clearFilter = {
                                val newFilter = state.mainScreenFilter.clearDateFilter()
                                action.filter(newFilter)
                            }
                        )
                    }
                }

                LazyColumn(
                    modifier =
                    Modifier
                        .padding(5.dp)
                ) {
                    val shownEntries =
                        state.entryContainerSt.value.filterEntries(state.mainScreenFilter)
                    items(
                        items = shownEntries,
                        key = { e -> e.id },
                        itemContent = { entry ->
                            val dismissState = rememberDismissState(
                                confirmStateChange = {
                                    if (it == DismissValue.DismissedToEnd ||
                                        it == DismissValue.DismissedToStart
                                    ) {
                                        executionScope.launch {
                                            action.removeEntry(entry)
                                        }
                                        true
                                    } else {
                                        false
                                    }
                                },
                            )

                            SwipeToDismiss(
                                state = dismissState,
                                background = { Color.Red },
                            ) {
                                EntryView(
                                    entry = entry,
                                    uploadEntry = {
                                        executionScope.launch {
                                            action.uploadEntry(it)
                                        }
                                    })
                            }
                        }
                    )
                }
            }

            val filter = state.mainScreenFilter
            if (isFromDatePickerOpen) {
                val fromDate = filter.fromDate ?: Date()
                MDatePickerDialog(
                    currentDate = fromDate,
                    dateType = DateType.Start,
                    onDatePick = {
                        val newFilter = filter.copy(fromDate = it)
                        action.filter(newFilter)
                    },
                    onDismiss = {
                        isFromDatePickerOpen = false
                    }
                )
            }
            if(isTagSelectionDialogOpen){
                TagPickerDialog(
                    tags = state.tagContainerSt.value.allValidTags,
                    initSelectedTags = selectedTagsForFilter,
                    onDone = {selectedTags->
                        val newFilter = state.mainScreenFilter.copy(tags=selectedTags)
                        selectedTagsForFilter = selectedTags
                        action.filter(newFilter)
                    },
                    onDismiss = {
                        isTagSelectionDialogOpen = false
                    }
                )
            }

            if (isToDatePickerOpen) {
                val toDate = filter.toDate ?: Date()
                MDatePickerDialog(
                    currentDate = toDate,
                    dateType = DateType.End,
                    onDatePick = {
                        val newFilter = filter.copy(toDate = it)
                        action.filter(newFilter)
                    },
                    onDismiss = {
                        isToDatePickerOpen = false
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun previewMainScreen() {
    PCRTheme(darkTheme = false) {
        MainScreen(
            state = MainScreenState.forPreview,
            action = MainScreenAction.forPreview,
            toEntryCreateScreen = {},
            toManageTagScreen = {},
            toFrontScreen = {},
        )
    }
}
