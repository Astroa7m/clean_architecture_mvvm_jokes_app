package com.astroscoding.jokesapp.presentation.screen.misc_jokes

import com.astroscoding.jokesapp.data.local.PreferencesManager
import com.astroscoding.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.astroscoding.jokesapp.domain.use_case.GetJokesUseCase
import com.astroscoding.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.astroscoding.jokesapp.presentation.core.BaseJokesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MiscJokesViewModel @Inject constructor(
    getJokesUseCase: GetJokesUseCase,
    favouriteJokesUseCase: FavouriteJokesUseCase,
    unFavouriteJokeUseCase: UnFavouriteJokeUseCase,
    preferencesManager: PreferencesManager
) : BaseJokesViewModel(getJokesUseCase, favouriteJokesUseCase, unFavouriteJokeUseCase, preferencesManager) {

    init {
        getAllJokes(category = listOf("Misc"))
    }

}