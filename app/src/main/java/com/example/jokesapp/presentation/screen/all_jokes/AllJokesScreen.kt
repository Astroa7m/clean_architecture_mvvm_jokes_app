package com.example.jokesapp.presentation.screen.all_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.R
import com.example.jokesapp.presentation.core.component.JokesScreen

@Composable
fun AllJokesScreen() {
    val viewModel : AllJokesViewModel = hiltViewModel()
    JokesScreen(label = "All", R.drawable.ic_misc, viewModel)
}