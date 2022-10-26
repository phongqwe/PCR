package com.qxdzbc.pcr.screen.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import com.qxdzbc.pcr.ui.theme.PCRTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

const val mainScreenNavTag = "MainScreen_NavTag"
@Composable
fun MainScreen (
    state:MainScreenState
){
    Surface(modifier= Modifier.fillMaxSize()) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val crScope = rememberCoroutineScope()
        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                UserInfo()
                DrawerItem("TODO: Manage tag",{})
                DrawerItem("TODO: switch theme",{})
                DrawerItem("TODO: logout",{})
            },

        ) {
            Column(modifier= Modifier.fillMaxSize()){
                Row(modifier=Modifier.fillMaxWidth()){
                    ConstraintLayout(modifier =Modifier.fillMaxWidth()) {
                        val (menuRef)=createRefs()
                        IconButton(
                            onClick = {
                                crScope.launch {
                                    drawerState.open()
                                }
                            },
                            modifier=Modifier.constrainAs(menuRef){
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "menu button",
                                tint=MaterialTheme.colors.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun previewMainScreen(){
    PCRTheme(darkTheme = true) {
        MainScreen(state = MainScreenState.preview())
    }
}
