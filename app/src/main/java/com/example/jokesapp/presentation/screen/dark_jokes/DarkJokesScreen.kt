package com.example.jokesapp.presentation.screen.dark_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.R
import com.example.jokesapp.presentation.core.component.JokesScreen
import com.example.jokesapp.presentation.core.util.Screens
import com.example.jokesapp.presentation.screen.all_jokes.AllJokesViewModel

@Composable
fun DarkJokesScreen() {
    val viewModel : DarkJokesViewModel = hiltViewModel()
    JokesScreen(label = "Dark", R.drawable.ic_dark, viewModel)
}