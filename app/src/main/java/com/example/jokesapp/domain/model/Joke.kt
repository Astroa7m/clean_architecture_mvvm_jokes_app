package com.example.jokesapp.domain.model

import com.example.jokesapp.data.local.model.JokeEntity
import com.example.jokesapp.data.remote.dto.Flags

data class Joke(
    val id: Int,
    val category: String,
    val joke: String?=null,
    val setup: String?=null,
    val delivery: String?=null,
    val flags: Flags,
    val lang: String,
    val safe: Boolean,
    val type: String,
    var isFavourite: Boolean
){
    fun toJokeEntity() = JokeEntity(
        id,
        category,
        joke,
        setup,
        delivery,
        flags,
        lang,
        safe,
        type
    )
}
