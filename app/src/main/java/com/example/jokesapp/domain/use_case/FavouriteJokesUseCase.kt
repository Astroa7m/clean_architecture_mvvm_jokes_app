package com.example.jokesapp.domain.use_case

import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.repository.JokesRepository
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