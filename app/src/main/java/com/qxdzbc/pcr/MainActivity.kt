package com.qxdzbc.pcr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.qxdzbc.pcr.action.UpdateUserAction
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.di.state.AppStateMs
import com.qxdzbc.pcr.err.ErrorRouter
import com.qxdzbc.pcr.screen.front_screen.FrontScreen
import com.qxdzbc.pcr.screen.front_screen.FrontScreenAction
import com.qxdzbc.pcr.screen.front_screen.state.FrontScreenState.Companion.frontScreenNavTag
import com.qxdzbc.pcr.screen.main_screen.MainScreen
import com.qxdzbc.pcr.screen.main_screen.mainScreenNavTag
import com.qxdzbc.pcr.state.app.AppState
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper.Companion.toWrapper
import com.qxdzbc.pcr.ui.theme.PCRTheme
import com.qxdzbc.pcr.util.FireAuthUtils.hasUser
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tagDao: TagDao

    @Inject
    @AppStateMs
    lateinit var appStateMs: Ms<AppState>

    @Inject
    lateinit var frontAction: FrontScreenAction

    @Inject
    lateinit var updateUserAction:UpdateUserAction

    @Inject
    lateinit var errorRouter: ErrorRouter

    val appState get() = appStateMs.value

    lateinit var navController: NavHostController
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        when (res.resultCode) {
            android.app.Activity.RESULT_OK -> {
                navController.navigate(mainScreenNavTag)
                updateUserAction.updateUser(FirebaseAuth.getInstance().currentUser?.toWrapper())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PCRTheme(darkTheme = appState.isDarkThemeMs.value) {
                val sysUiController = rememberSystemUiController()
                sysUiController.setSystemBarsColor(
                    color = MaterialTheme.colors.primaryVariant,
                    darkIcons = !appState.isDarkThemeMs.value
                )
                navController = rememberNavController()
                NavHost(navController, startDestination = frontScreenNavTag) {
                    composable(frontScreenNavTag) {
                        FrontScreen(
                            action = frontAction,
                            state = appState.frontScreenStateMs.value,
                            login = { launchAuthUI() }
                        )
                    }
                    composable(mainScreenNavTag) {
                        MainScreen()
                        BackHandler(true) {
                            if(hasUser()){
                                /*
                                 do nothing, prevent user from
                                 going back to front screen once
                                 they have login
                                 */
                            }else{
                                onBackPressed()
                            }
                        }
                    }
                }
                toMainIfPossible()
            }
        }
    }

    private fun launchAuthUI() {
        if (hasUser().not()) {
            val providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().setAllowNewAccounts(false).build()
            )
            val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build()
            signInLauncher.launch(intent)
        }
    }

    /**
     * skip front screen and go straight to main screen if the user has already signed in.
     */
    private fun toMainIfPossible(){
        if (FirebaseAuth.getInstance().currentUser != null) {
            navController.navigate(mainScreenNavTag)
        }
    }
}
