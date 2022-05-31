package com.example.jokesapp.data.remote

import com.example.jokesapp.data.remote.dto.JokesDto
import com.example.jokesapp.data.remote.dto.PostJokeSingleDto
import com.example.jokesapp.data.remote.dto.PostJokeTwoPartDto

interface JokesRemoteSource {

    suspend fun getJokes(
        category: String = "Any",
        queryString: String = "",
        type: String = "",
        count: Int,
        blacklistFlags: String = ""
    ): JokesDto

    suspend fun addJoke(
        jokeSingle: PostJokeSingleDto? = null,
        jokeTwoPart: PostJokeTwoPartDto? = null
    )

}