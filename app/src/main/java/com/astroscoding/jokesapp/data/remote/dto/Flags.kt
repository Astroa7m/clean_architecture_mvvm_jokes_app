package com.astroscoding.jokesapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    val explicit: Boolean,
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)