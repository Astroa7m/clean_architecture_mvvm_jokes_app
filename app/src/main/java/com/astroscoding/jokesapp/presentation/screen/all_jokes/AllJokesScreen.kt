package com.astroscoding.jokesapp.presentation.screen.all_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.R
import com.astroscoding.jokesapp.presentation.core.component.JokesScreen
import com.astroscoding.jokesapp.presentation.core.util.Screens

@Composable
fun AllJokesScreen() {
    val viewModel : AllJokesViewModel = hiltViewModel()
    JokesScreen(label = Screens.AllJokeScreen.capitalizeFirst(), Screens.AllJokeScreen.icon, viewModel)
}