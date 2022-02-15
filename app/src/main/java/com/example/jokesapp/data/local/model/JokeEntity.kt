package com.example.jokesapp.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jokesapp.data.remote.dto.Flags
import com.example.jokesapp.domain.model.Joke

@Entity(tableName = "jokes_table")
data class JokeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val category: String,
    val joke: String?=null,
    val setup: String?=null,
    val delivery: String?=null,
    @Embedded
    val flags: Flags,
    val lang: String,
    val safe: Boolean,
    val type: String
){
    fun toJoke() = Joke(
        id,
        category,
        joke,
        setup,
        delivery,
        flags,
        lang,
        safe,
        type,
        true
    )
}
