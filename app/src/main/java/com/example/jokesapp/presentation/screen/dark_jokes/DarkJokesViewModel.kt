package com.example.jokesapp.presentation.screen.dark_jokes

import androidx.lifecycle.ViewModel
import com.example.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.example.jokesapp.domain.use_case.GetJokesUseCase
import com.example.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.example.jokesapp.presentation.core.BaseJokesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DarkJokesViewModel @Inject constructor(
    getAllJokes: GetJokesUseCase,
    favouriteJokesUseCase: FavouriteJokesUseCase,
    unFavouriteJokeUseCase: UnFavouriteJokeUseCase
) : BaseJokesViewModel(getAllJokes, favouriteJokesUseCase, unFavouriteJokeUseCase) {

    init {
        getAllJokes(category = listOf("Dark"))
    }

}