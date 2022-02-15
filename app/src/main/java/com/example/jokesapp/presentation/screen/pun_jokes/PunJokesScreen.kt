package com.example.jokesapp.presentation.screen.pun_jokes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.R
import com.example.jokesapp.presentation.core.component.JokesScreen
import com.example.jokesapp.presentation.screen.programming_jokes.ProgrammingJokesViewModel

@Composable
fun PunJokesScreen() {
    val viewModel : PunJokesViewModel = hiltViewModel()
    JokesScreen(label = "Pun", R.drawable.ic_pun, viewModel)
}