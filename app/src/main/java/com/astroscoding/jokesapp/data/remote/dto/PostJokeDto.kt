package com.astroscoding.jokesapp.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class PostJokeSingleDto(
   val category: String,
   val flags: Flags,
   val joke: String,
   val formatVersion: Int,
   val lang: String,
   val type: String
)


@Serializable
data class PostJokeTwoPartDto(
   val category: String,
   val flags: Flags,
   val delivery: String,
   val setup: String,
   val formatVersion: Int,
   val lang: String,
   val type: String
)
