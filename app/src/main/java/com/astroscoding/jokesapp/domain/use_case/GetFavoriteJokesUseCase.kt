package com.astroscoding.jokesapp.domain.use_case

import com.astroscoding.jokesapp.domain.model.Joke
import com.astroscoding.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteJokesUseCase @Inject constructor(
    private val repo: JokesRepository
) {

    operator fun invoke(): Flow<List<Joke>> {
        return repo.getFavouriteJokes()
    }

}