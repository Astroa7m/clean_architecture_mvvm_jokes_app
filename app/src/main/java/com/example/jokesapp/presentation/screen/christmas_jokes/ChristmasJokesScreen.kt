package com.example.jokesapp.presentation.screen.christmas_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.R
import com.example.jokesapp.presentation.core.component.JokesScreen
import com.example.jokesapp.presentation.screen.pun_jokes.PunJokesViewModel

@Composable
fun ChristmasJokesScreen() {
    val viewModel : ChristmasJokesViewModel = hiltViewModel()
    JokesScreen(label = "Christmas", R.drawable.ic_christmas, viewModel)
}