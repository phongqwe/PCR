package com.qxdzbc.pcr.screen.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.screen.common.MIconButton
import com.qxdzbc.pcr.screen.common.PCRTopAppBar
import com.qxdzbc.pcr.screen.common.ThemeSwitcher
import com.qxdzbc.pcr.screen.main_screen.action.MainScreenAction
import com.qxdzbc.pcr.screen.main_screen.date_filter_view.DateView
import com.qxdzbc.pcr.screen.main_screen.date_filter_view.MDatePickerDialog
import com.qxdzbc.pcr.screen.main_screen.entry_view.EntryView
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import com.qxdzbc.pcr.ui.theme.PCRTheme
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun MainScreen(
    state: MainScreenState,
    action: MainScreenAction,
    toEntryCreateScreen:()->Unit
) {
    val crScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val userId: String? = state.userSt.value?.uid
    var isFromDatePickerOpen: Boolean by remember { ms(false) }
    var isToDatePickerOpen: Boolean by remember { ms(false) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            PCRTopAppBar {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (menuRef) = createRefs()
                    MIconButton(
                        imageVector=Icons.Default.Menu,
                        onClick = {
                            crScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        },
                        modifier = Modifier.constrainAs(menuRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                    )
                }
            }
        },
        drawerContent = {
            UserInfo(state.userSt.value ?: FirebaseUserWrapper.forPreview)
            ClickableDrawerItem("TODO: Manage tag") {}
            DrawerItem {
                ThemeSwitcher(isDark = state.isDark, switchTheme = {
                    crScope.launch {
                        scaffoldState.drawerState.close()
                        action.switchTheme(it)
                    }
                })
            }
            ClickableDrawerItem("TODO: logout") {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { toEntryCreateScreen() },
                elevation = FloatingActionButtonDefaults.elevation(10.dp),
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    Icons.Filled.Add,
                    "",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
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
                                .align(Alignment.Center)
                        )
                    }
                }

                LazyColumn(modifier = Modifier.padding(5.dp)) {
                    val shownEntries =
                        state.entryContainerSt.value.filterEntries(state.mainScreenFilter)
                    items(shownEntries) { entry ->
                        EntryView(
                            entry = entry,
                            uploadEntry = { /*TODO*/ })
                    }
                }
            }

            val filter = state.mainScreenFilter
            if (isFromDatePickerOpen) {
                val fromDate = filter.fromDate ?: Date()
                MDatePickerDialog(
                    currentDate = fromDate,
                    isFromDate=true,
                    onDatePick = {
                        val newFilter = filter.copy(fromDate = it)
                        action.filter(newFilter)
                    },
                    onDismiss = {
                        isFromDatePickerOpen = false
                    }
                )
            }

            if (isToDatePickerOpen) {
                val toDate = filter.toDate ?: Date()
                    MDatePickerDialog(
                        currentDate = toDate,
                        isFromDate=false,
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
            toEntryCreateScreen = {}
        )
    }
}
