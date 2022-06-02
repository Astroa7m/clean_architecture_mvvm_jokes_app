package com.astroscoding.jokesapp.presentation.screen.pun_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.R
import com.astroscoding.jokesapp.presentation.core.component.JokesScreen
import com.astroscoding.jokesapp.presentation.core.util.Screens

@Composable
fun PunJokesScreen() {
    val viewModel : PunJokesViewModel = hiltViewModel()
   JokesScreen(label = Screens.PunJokeScreen.capitalizeFirst(), Screens.PunJokeScreen.icon, viewModel)
}