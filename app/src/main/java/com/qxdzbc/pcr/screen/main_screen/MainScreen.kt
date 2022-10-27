package com.qxdzbc.pcr.screen.main_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.michaelbull.result.onSuccess
import com.google.firebase.auth.FirebaseAuth
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.firestore.EntryDoc
import com.qxdzbc.pcr.firestore.FirebaseHelperImp
import com.qxdzbc.pcr.firestore.TagDoc
import com.qxdzbc.pcr.screen.common.CommonTopAppBar
import com.qxdzbc.pcr.screen.common.ThemeSwitcher
import com.qxdzbc.pcr.screen.main_screen.action.MainScreenAction
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
        Box(modifier = Modifier.padding(contentPadding)) {
            Column {
                Button(onClick = {
                    userId?.also {
                        crScope.launch {
                            state
                        }
                    }
                }) {
                    Text("Read entry")
                }
            }
        }
    }
}

@Composable
@Preview
fun previewMainScreen() {
    PCRTheme(darkTheme = true) {
        MainScreen(
            state = MainScreenState.forPreview,
            action = MainScreenAction.forPreview
        )
    }
}
