package com.example.jokesapp.presentation.screen.spooky_jokes

import com.example.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.example.jokesapp.domain.use_case.GetJokesUseCase
import com.example.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.example.jokesapp.presentation.core.BaseJokesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpookyJokesViewModel @Inject constructor(
    getJokesUseCase: GetJokesUseCase,
    favouriteJokesUseCase: FavouriteJokesUseCase,
    unFavouriteJokeUseCase: UnFavouriteJokeUseCase
) : BaseJokesViewModel(getJokesUseCase, favouriteJokesUseCase, unFavouriteJokeUseCase) {

    init {
        getAllJokes(category = listOf("Spooky"))
    }

}