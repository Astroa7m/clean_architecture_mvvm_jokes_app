package com.example.jokesapp.domain.use_case

import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteJokesUseCase @Inject constructor(
    private val repo: JokesRepository
) {

    operator fun invoke(): Flow<List<Joke>> {
        return repo.getFavouriteJokes()
    }

}