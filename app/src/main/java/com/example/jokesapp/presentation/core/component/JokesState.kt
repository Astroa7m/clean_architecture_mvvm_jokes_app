package com.example.jokesapp.presentation.core.component

import com.example.jokesapp.domain.model.Joke

data class JokesState(
    val isLoading: Boolean = false,
    val jokes: List<Joke> = emptyList(),
    val errorMessage: String = ""
)
