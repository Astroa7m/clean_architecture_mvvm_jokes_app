package com.example.jokesapp.presentation.screen.favourite_jokes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.example.jokesapp.domain.use_case.GetFavoriteJokesUseCase
import com.example.jokesapp.domain.use_case.UnFavouriteAllJokesUseCase
import com.example.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.example.jokesapp.presentation.core.component.JokesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteJokesViewModel @Inject constructor(
    private val getFavoriteJokesUseCase: GetFavoriteJokesUseCase,
    private val favouriteJokesUseCase: FavouriteJokesUseCase,
    private val unFavouriteJokeUseCase: UnFavouriteJokeUseCase,
    private val unFavouriteAllJokesUseCase: UnFavouriteAllJokesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(JokesState())
    val state: State<JokesState> = _state

    init {
        getFavouriteJokes()
    }

    private fun getFavouriteJokes() {
        getFavoriteJokesUseCase()
            .onEach {
                _state.value = _state.value.copy(
                    jokes = it,
                )
            }.launchIn(viewModelScope)
    }

    fun onFavouriteItemClicked(joke: Joke) {
        val list = state.value.jokes.map {
            if (joke == it) {
                viewModelScope.launch {
                    if (!it.isFavourite)
                        favouriteJokesUseCase(it)
                    else
                        unFavouriteJokeUseCase(it)
                }
                it.copy(isFavourite = !it.isFavourite)
            } else
                it
        }
        _state.value = state.value.copy(
            jokes = list,
            errorMessage = ""
        )

    }

    fun unFavouriteAllJokes() {
        viewModelScope.launch{
            unFavouriteAllJokesUseCase.invoke()
        }
    }
}