package com.example.jokesapp.presentation.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.core.Resource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.use_case.FavouriteJokesUseCase
import com.example.jokesapp.domain.use_case.GetJokesUseCase
import com.example.jokesapp.domain.use_case.UnFavouriteJokeUseCase
import com.example.jokesapp.presentation.core.component.DropDownMenuItemModel
import com.example.jokesapp.presentation.core.component.JokesState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseJokesViewModel(
    private val getJokesUseCase: GetJokesUseCase,
    private val favouriteJokesUseCase: FavouriteJokesUseCase,
    private val unFavouriteJokeUseCase: UnFavouriteJokeUseCase
) : ViewModel() {

    private val _state = mutableStateOf(JokesState())
    val state: State<JokesState> = _state

    private val _event = MutableSharedFlow<UIEvent>()
    val event = _event.asSharedFlow()

    private var jokesPreference = listOf(false, false, false)

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun getAllJokes(hasStartedSearching: Boolean = false, count: Int = 10, category: List<String> = listOf("Any"), isFiltering: Boolean = false) {
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
                    _event.emit(UIEvent.SnackBar(result.message.toString()))
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

    fun onFavouriteItemClicked(joke: Joke) {
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

    fun onFiltered(filteringList: List<DropDownMenuItemModel>) {
        jokesPreference = filteringList.map { it.isChecked }
    }

}

sealed class UIEvent{
    data class SnackBar(val message: String) : UIEvent()
}
