package com.example.jokesapp.presentation.screen.christmas_jokes

import androidx.lifecycle.ViewModel
import com.example.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.example.jokesapp.domain.use_case.GetJokesUseCase
import com.example.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.example.jokesapp.presentation.core.BaseJokesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChristmasJokesViewModel @Inject constructor(
    getJokesUseCase: GetJokesUseCase,
    favouriteJokesUseCase: FavouriteJokesUseCase,
    unFavouriteJokeUseCase: UnFavouriteJokeUseCase
) : BaseJokesViewModel(getJokesUseCase, favouriteJokesUseCase, unFavouriteJokeUseCase) {

    init {
        getAllJokes(category = listOf("Christmas"))
    }

}