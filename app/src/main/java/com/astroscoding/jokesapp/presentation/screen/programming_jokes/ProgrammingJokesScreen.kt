package com.astroscoding.jokesapp.presentation.screen.programming_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.R
import com.astroscoding.jokesapp.presentation.core.component.JokesScreen
import com.astroscoding.jokesapp.presentation.core.util.Screens

@Composable
fun ProgrammingJokeScreen() {
    val viewModel : ProgrammingJokesViewModel = hiltViewModel()
    JokesScreen(label = Screens.ProgrammingJokeScreen.capitalizeFirst(), Screens.ProgrammingJokeScreen.icon, viewModel)
}