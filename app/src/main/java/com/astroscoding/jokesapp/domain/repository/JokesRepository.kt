package com.astroscoding.jokesapp.domain.repository

import com.astroscoding.jokesapp.core.Resource
import com.astroscoding.jokesapp.domain.model.Joke
import com.astroscoding.jokesapp.domain.model.PostJoke
import kotlinx.coroutines.flow.Flow

interface JokesRepository {

    fun getAllJokes(
        category: List<String>,
        queryString: String,
        type: String,
        count: Int,
        blacklistFlags: List<String>
    ) : Flow<Resource<List<Joke>>>

    fun getFavouriteJokes() : Flow<List<Joke>>

    suspend fun unFavouriteJoke(joke: Joke)

    suspend fun unFavouriteAllJokes()

    suspend fun addJokesToFavourite(joke: Joke)

    suspend fun addJoke(joke: PostJoke) : Resource<Unit>

}