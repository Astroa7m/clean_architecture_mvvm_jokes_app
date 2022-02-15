package com.example.jokesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jokesapp.presentation.core.component.NavController
import com.example.jokesapp.presentation.screen.all_jokes.AllJokesScreen
import com.example.jokesapp.presentation.screen.home.HomeScreen
import com.example.jokesapp.presentation.ui.theme.JokesAppTheme
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
