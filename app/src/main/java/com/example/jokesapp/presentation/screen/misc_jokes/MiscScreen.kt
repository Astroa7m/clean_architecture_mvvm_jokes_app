package com.example.jokesapp.presentation.screen.misc_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.R
import com.example.jokesapp.presentation.core.component.JokesScreen
import com.example.jokesapp.presentation.core.util.Screens

@Composable
fun MiscJokeScreen() {
    val viewModel : MiscJokesViewModel = hiltViewModel()
    JokesScreen(label = "Misc", R.drawable.ic_all, viewModel)
}