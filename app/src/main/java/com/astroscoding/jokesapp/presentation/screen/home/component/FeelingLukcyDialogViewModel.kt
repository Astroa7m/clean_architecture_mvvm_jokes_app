package com.astroscoding.jokesapp.presentation.screen.home.component

import com.astroscoding.jokesapp.data.local.PreferencesManager
import com.astroscoding.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.astroscoding.jokesapp.domain.use_case.GetJokesUseCase
import com.astroscoding.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.astroscoding.jokesapp.presentation.core.BaseJokesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeelingLuckyDialogViewModel @Inject constructor(
    getAllJokes: GetJokesUseCase,
    favouriteJokesUseCase: FavouriteJokesUseCase,
    unFavouriteJokeUseCase: UnFavouriteJokeUseCase,
    preferencesManager: PreferencesManager
) : BaseJokesViewModel(getAllJokes, favouriteJokesUseCase, unFavouriteJokeUseCase, preferencesManager) {

    init {
        getAllJokes(count = 2)
    }

}