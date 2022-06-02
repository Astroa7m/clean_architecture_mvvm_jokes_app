package com.astroscoding.jokesapp.presentation.core.component

import com.astroscoding.jokesapp.domain.model.Joke

data class JokesState(
    val isLoading: Boolean = false,
    val jokes: List<Joke> = emptyList(),
    val errorMessage: String = ""
)
