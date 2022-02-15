package com.example.jokesapp.presentation.screen.programming_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.R
import com.example.jokesapp.presentation.core.component.JokesScreen

@Composable
fun ProgrammingJokeScreen() {
    val viewModel : ProgrammingJokesViewModel = hiltViewModel()
    JokesScreen(label = "Programming", R.drawable.ic_code, viewModel)
}