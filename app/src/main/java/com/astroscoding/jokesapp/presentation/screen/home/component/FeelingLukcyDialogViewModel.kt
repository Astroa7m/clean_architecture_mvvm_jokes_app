package com.astroscoding.jokesapp.presentation.screen.home.component

import androidx.lifecycle.viewModelScope
import com.astroscoding.jokesapp.data.local.PreferencesManager
import com.astroscoding.jokesapp.domain.model.Joke
import com.astroscoding.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.astroscoding.jokesapp.domain.use_case.GetJokesUseCase
import com.astroscoding.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.astroscoding.jokesapp.presentation.core.BaseJokesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeelingLuckyDialogViewModel @Inject constructor(
    getJokesUseCase: GetJokesUseCase,
    private val favouriteJokesUseCase: FavouriteJokesUseCase,
    private val unFavouriteJokeUseCase: UnFavouriteJokeUseCase,
    preferencesManager: PreferencesManager
) : BaseJokesViewModel(getJokesUseCase, favouriteJokesUseCase, unFavouriteJokeUseCase, preferencesManager) {

    init {
        getAllJokes(count = 2)
    }

    override fun onFavouriteItemClicked(joke: Joke, favourite: Boolean?) {
        viewModelScope.launch {
            if(favourite == true)
                favouriteJokesUseCase(joke)
            else
                unFavouriteJokeUseCase(joke)
        }
    }

}