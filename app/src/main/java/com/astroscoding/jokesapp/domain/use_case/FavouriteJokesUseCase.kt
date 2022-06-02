package com.astroscoding.jokesapp.domain.use_case

import com.astroscoding.jokesapp.domain.model.Joke
import com.astroscoding.jokesapp.domain.repository.JokesRepository
import javax.inject.Inject

class FavouriteJokesUseCase @Inject constructor(
    private val repo: JokesRepository
) {

    suspend operator fun invoke(joke: Joke?){
        joke?.let {
            repo.addJokesToFavourite(it)
        }
    }

}