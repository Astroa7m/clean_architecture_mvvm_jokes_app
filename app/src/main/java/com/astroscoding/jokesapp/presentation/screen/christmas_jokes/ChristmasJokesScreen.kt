package com.astroscoding.jokesapp.presentation.screen.christmas_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.R
import com.astroscoding.jokesapp.presentation.core.component.JokesScreen
import com.astroscoding.jokesapp.presentation.core.util.Screens

@Composable
fun ChristmasJokesScreen() {
    val viewModel : ChristmasJokesViewModel = hiltViewModel()
    JokesScreen(label = Screens.Christmas.capitalizeFirst(), Screens.Christmas.icon, viewModel)
}