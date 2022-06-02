package com.astroscoding.jokesapp.domain.model

import com.astroscoding.jokesapp.data.remote.dto.Flags
import com.astroscoding.jokesapp.data.remote.dto.PostJokeSingleDto
import com.astroscoding.jokesapp.data.remote.dto.PostJokeTwoPartDto

sealed class PostJoke(
    val category: String,
    val delivery: String?,
    val flags: Flags,
    val lang: String,
    val joke: String?,
    val setup: String?,
    val type: String
) {

    class PostJokeSingle(
        category: String,
        flags: Flags,
        lang: String,
        joke: String,
        type: String
    ) : PostJoke(category, null, flags, lang, joke, null, type) {
        fun toPostJokeSingleDto() = PostJokeSingleDto(
            category,
            flags,
            joke!!,
            3,
            lang,
            type
        )
    }

    class PostJokeTwoPart(
        category: String,
        delivery: String,
        flags: Flags,
        lang: String,
        setup: String,
        type: String
    ) : PostJoke(category, delivery, flags, lang, null, setup, type) {
        fun toPostJokeTwoPartDto() = PostJokeTwoPartDto(
            category,
            flags,
            delivery!!,
            setup!!,
            3,
            lang,
            type
        )
    }
}
