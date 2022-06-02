package com.astroscoding.jokesapp.presentation.screen.dark_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.R
import com.astroscoding.jokesapp.presentation.core.component.JokesScreen
import com.astroscoding.jokesapp.presentation.core.util.Screens

@Composable
fun DarkJokesScreen() {
    val viewModel : DarkJokesViewModel = hiltViewModel()
    JokesScreen(label = Screens.DarkJokeScreen.capitalizeFirst(), Screens.DarkJokeScreen.icon, viewModel)
}