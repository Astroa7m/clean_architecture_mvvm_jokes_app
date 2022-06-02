package com.astroscoding.jokesapp.data.remote.dto

import com.astroscoding.jokesapp.domain.model.Joke
import kotlinx.serialization.Serializable

@Serializable
data class JokeDto(
    val category: String,
    val delivery: String?=null,
    val flags: Flags,
    val id: Int,
    val joke: String?=null,
    val lang: String,
    val safe: Boolean,
    val setup: String?=null,
    val type: String
){
    fun toDomainJokes() = Joke(
        id,
        category,
        joke,
        setup,
        delivery,
        flags,
        lang,
        safe,
        type,
        false
    )
}