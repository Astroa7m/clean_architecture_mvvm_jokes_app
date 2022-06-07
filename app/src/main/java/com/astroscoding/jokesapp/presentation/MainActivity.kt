package com.astroscoding.jokesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.presentation.core.SplashScreenViewModel
import com.astroscoding.jokesapp.presentation.core.component.NavController
import com.astroscoding.jokesapp.presentation.ui.theme.JokesAppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: SplashScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
            .setKeepOnScreenCondition {
                !viewModel.isLoading.value
            }
        setContent {
            JokesAppTheme {
                val startDestination by viewModel.startDestination
                val navController = rememberAnimatedNavController()
                NavController(startDestination, navController)
            }
        }
    }

}
