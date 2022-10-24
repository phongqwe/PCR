package com.qxdzbc.pcr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.qxdzbc.pcr.action.Action1
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.di.state.AppStateMs
import com.qxdzbc.pcr.screen.front_screen.FrontScreen
import com.qxdzbc.pcr.screen.front_screen.FrontScreenAction
import com.qxdzbc.pcr.screen.front_screen.frontScreenNavTag
import com.qxdzbc.pcr.screen.main_screen.MainScreen
import com.qxdzbc.pcr.screen.main_screen.mainScreenNavTag
import com.qxdzbc.pcr.state.AppState
import com.qxdzbc.pcr.ui.theme.PCRTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var action1: Action1
    @Inject
    lateinit var tagDao: TagDao

    @Inject
    @AppStateMs
    lateinit var appStateMs:Ms<AppState>

    @Inject
    lateinit var frontAction:FrontScreenAction

    val appState get()=appStateMs.value

    lateinit var navController:NavHostController
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        when(res.resultCode){

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PCRTheme(darkTheme = appState.isDarkThemeMs.value) {
                val sysUiController = rememberSystemUiController()
                sysUiController.setSystemBarsColor(
                    color=MaterialTheme.colors.primaryVariant,
                    darkIcons = !appState.isDarkThemeMs.value
                )
                navController = rememberNavController()
                val fsTag = frontScreenNavTag
                val msTag = mainScreenNavTag
                NavHost(navController, startDestination = fsTag) {
                    composable(fsTag) {
                        FrontScreen(
                            action = frontAction,
                            state=appState.frontScreenStateMs.value,
                            login = {}
                            )
                    }
                    composable(msTag) {
                        MainScreen()
                    }
                }
            }
        }
    }
}
