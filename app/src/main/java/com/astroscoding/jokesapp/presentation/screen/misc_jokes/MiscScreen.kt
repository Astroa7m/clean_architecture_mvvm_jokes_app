package com.astroscoding.jokesapp.presentation.screen.misc_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.R
import com.astroscoding.jokesapp.presentation.core.component.JokesScreen
import com.astroscoding.jokesapp.presentation.core.util.Screens

@Composable
fun MiscJokeScreen() {
    val viewModel : MiscJokesViewModel = hiltViewModel()
    JokesScreen(label = Screens.MiscJokeScreen.capitalizeFirst(), Screens.MiscJokeScreen.icon, viewModel)
}