package com.qxdzbc.pcr.screen.main_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.michaelbull.result.onSuccess
import com.google.firebase.auth.FirebaseAuth
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.firestore.EntryDoc
import com.qxdzbc.pcr.firestore.FirebaseHelperImp
import com.qxdzbc.pcr.firestore.TagDoc
import com.qxdzbc.pcr.screen.common.CommonTopAppBar
import com.qxdzbc.pcr.screen.common.ThemeSwitcher
import com.qxdzbc.pcr.screen.main_screen.action.MainScreenAction
import com.qxdzbc.pcr.screen.main_screen.entry_view.EntryView
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.ui.theme.PCRTheme
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    state: MainScreenState,
    action: MainScreenAction,
) {
    val crScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val userId:String? = state.userSt.value?.uid
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommonTopAppBar {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                            val (menuRef) = createRefs()
                            IconButton(
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
                            ) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "menu button",
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            }
                        }
                    }
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
    ) { contentPadding ->
        MBox(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {
            Column {
                Card(modifier=Modifier.fillMaxWidth().padding(5.dp),elevation =10.dp){
                    MBox(modifier=Modifier.fillMaxWidth().padding(top=10.dp,bottom =10.dp)){
                        val dateStr =state.mainScreenFilter?.dateDisplayText
                        if(dateStr!=null){
                            Text(dateStr,modifier=Modifier.align(Alignment.Center))
                        }else{
                            Text("<<Date period>>",modifier=Modifier.align(Alignment.Center))
                        }

                    }
                }

                LazyColumn(modifier=Modifier.padding(5.dp)){
                    items(state.entryContainerSt.value.filterEntries(state.mainScreenFilter)){entry->
                            EntryView(
                                entry = entry,
                                uploadEntry = { /*TODO*/ })
                    }
                }
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
            action = MainScreenAction.forPreview
        )
    }
}
