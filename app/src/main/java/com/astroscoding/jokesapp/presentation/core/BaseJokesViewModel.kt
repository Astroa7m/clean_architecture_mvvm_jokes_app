package com.astroscoding.jokesapp.presentation.core

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astroscoding.jokesapp.core.Resource
import com.astroscoding.jokesapp.data.local.PreferencesManager
import com.astroscoding.jokesapp.domain.model.Joke
import com.astroscoding.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.astroscoding.jokesapp.domain.use_case.GetJokesUseCase
import com.astroscoding.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.astroscoding.jokesapp.presentation.core.component.JokesState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseJokesViewModel(
    private val getJokesUseCase: GetJokesUseCase,
    private val favouriteJokesUseCase: FavouriteJokesUseCase,
    private val unFavouriteJokeUseCase: UnFavouriteJokeUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _state = mutableStateOf(JokesState())
    open val state: State<JokesState> = _state

    protected val _event = Channel<UIEvent>()
    val event = _event.consumeAsFlow()

    var jokesPreference by mutableStateOf(Pair(false, false))
        private set

    private val preferences = preferencesManager.jokesPref

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    init {
        observePref()
    }

    private fun observePref(){
        preferences.onEach {
            jokesPreference = it
        }.launchIn(viewModelScope)
    }

    open fun getAllJokes(hasStartedSearching: Boolean = false, count: Int = 10, category: List<String> = listOf("Any"), isFiltering: Boolean = false) {
        getJokesUseCase(
            category,
            searchQuery.value.trim(),
            jokesPreference,
            count,
            emptyList()
        ).onEach { result ->
            // cleaning the current data so once we search, the data is populated correctly
            if (hasStartedSearching || isFiltering)
                _state.value = state.value.copy(jokes = emptyList())
            // adding new items to the current data in pagination
            val newList = state.value.jokes.toMutableList().apply {
                result.data?.forEach {
                    add(it)
                }
            }.distinctBy { it.id }
            when (result) {
                is Resource.Failure -> {
                    _state.value = state.value.copy(
                        jokes = newList,
                        isLoading = false,
                        errorMessage = result.message ?: "Error occurred"
                    )
                    _event.send(UIEvent.SnackBar(result.message.toString()))
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        jokes = newList,
                        isLoading = true,
                        errorMessage = ""
                    )
                }
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        jokes = newList,
                        isLoading = false,
                        errorMessage = ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    open fun onFavouriteItemClicked(joke: Joke, favourite: Boolean? = null) {
        val list = state.value.jokes.map {
            if (joke == it) {
                viewModelScope.launch {
                    if(!it.isFavourite)
                        favouriteJokesUseCase(it)
                    else
                        unFavouriteJokeUseCase(it)
                }
                it.copy(isFavourite = !it.isFavourite)
            }
            else
                it
        }
        _state.value = state.value.copy(
            jokes = list,
            errorMessage = ""
        )

    }

    fun onSearchQueryTextChanged(value: String) {
        _searchQuery.value = value
    }

    fun onDoneSearching() {
        _searchQuery.value = ""
        _state.value = _state.value.copy(jokes = emptyList())
    }

    fun onFiltered(filteringItems: Pair<Boolean, Boolean>) {
        viewModelScope.launch {
            launch {
                preferencesManager.setJokesType(filteringItems)
            }.join()
            getAllJokes(isFiltering = true)
        }
    }

}

sealed class UIEvent{
    data class SnackBar(val message: String) : UIEvent()
}
