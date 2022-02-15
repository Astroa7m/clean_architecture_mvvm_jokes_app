package com.example.jokesapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class JokesDto(
    val amount: Int,
    val error: Boolean,
    val jokes: List<JokeDto>
)