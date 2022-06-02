package com.astroscoding.jokesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.astroscoding.jokesapp.presentation.core.component.NavController
import com.astroscoding.jokesapp.presentation.ui.theme.JokesAppTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokesAppTheme {
                NavController()
            }
        }
    }
}
