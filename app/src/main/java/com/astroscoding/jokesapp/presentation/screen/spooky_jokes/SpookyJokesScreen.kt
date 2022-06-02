package com.astroscoding.jokesapp.presentation.screen.spooky_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.R
import com.astroscoding.jokesapp.presentation.core.component.JokesScreen
import com.astroscoding.jokesapp.presentation.core.util.Screens

@Composable
fun SpookyJokesScreen() {
    val viewModel : SpookyJokesViewModel = hiltViewModel()
   JokesScreen(label = Screens.SpookyJokeScreen.capitalizeFirst(), Screens.SpookyJokeScreen.icon, viewModel)
}