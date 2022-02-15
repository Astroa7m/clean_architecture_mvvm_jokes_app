package com.example.jokesapp.presentation.screen.home.component

import androidx.lifecycle.ViewModel
import com.example.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.example.jokesapp.domain.use_case.GetJokesUseCase
import com.example.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.example.jokesapp.presentation.core.BaseJokesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeelingLuckyDialogViewModel @Inject constructor(
    getAllJokes: GetJokesUseCase,
    favouriteJokesUseCase: FavouriteJokesUseCase,
    unFavouriteJokeUseCase: UnFavouriteJokeUseCase
) : BaseJokesViewModel(getAllJokes, favouriteJokesUseCase, unFavouriteJokeUseCase) {

    init {
        getAllJokes(count = 2)
    }

}