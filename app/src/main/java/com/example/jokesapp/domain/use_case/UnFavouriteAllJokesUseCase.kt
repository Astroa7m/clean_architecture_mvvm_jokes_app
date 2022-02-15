package com.example.jokesapp.domain.use_case

import com.example.jokesapp.domain.repository.JokesRepository
import javax.inject.Inject

class UnFavouriteAllJokesUseCase @Inject constructor(
    private val repo: JokesRepository
) {

    suspend operator fun invoke() = repo.unFavouriteAllJokes()

}