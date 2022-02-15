package com.example.jokesapp.presentation.screen.spooky_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.R
import com.example.jokesapp.presentation.core.component.JokesScreen
import com.example.jokesapp.presentation.core.util.Screens
import com.example.jokesapp.presentation.screen.pun_jokes.PunJokesViewModel

@Composable
fun SpookyJokesScreen() {
    val viewModel : SpookyJokesViewModel = hiltViewModel()
    JokesScreen(label = "Spooky", R.drawable.ic_spooky, viewModel)
}