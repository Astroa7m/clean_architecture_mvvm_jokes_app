package com.example.jokesapp.presentation.screen.add_joke

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.core.Resource
import com.example.jokesapp.domain.use_case.AddJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddJokeViewModel @Inject constructor(
    private val addJokeUseCase: AddJokeUseCase
) : ViewModel() {

    private val _submissionResult = MutableSharedFlow<Resource<Unit>>()
    val submissionResult = _submissionResult.asSharedFlow()

    private val _categoryText = mutableStateOf("Programming")
    fun setCategory(value: String) {
        _categoryText.value = value
    }

    private val _setupText = mutableStateOf("")
    val setupText: State<String> = _setupText

    fun onSetupTextChanged(value: String) {
        _setupText.value = value
    }

    private val _deliveryText = mutableStateOf("")
    val deliveryText: State<String> = _deliveryText

    fun onDeliveryTextChanged(value: String) {
        _deliveryText.value = value
    }

    private val _joke = mutableStateOf("")
    val joke: State<String> = _joke

    fun onJokeTextChanged(value: String) {
        _joke.value = value
    }

    private val _customLanguageText = mutableStateOf("")
    val customLanguageText: State<String> = _customLanguageText

    fun onCustomLanguageTextChanged(value: String) {
        _customLanguageText.value = value
    }

    fun addJoke(isSingle: Boolean) = viewModelScope.launch {
        if (isSingle) {

            if(_joke.value.isBlank() && _categoryText.value.isBlank() && _customLanguageText.value.isBlank())
                return@launch _submissionResult.emit(Resource.Failure("Please fill the given fields"))

            _submissionResult.emit(Resource.Loading())
            val result = addJokeUseCase(
                _categoryText.value,
                null,
                _customLanguageText.value,
                joke.value,
                null,
                "single"
            )
            if (result is Resource.Success)
                _submissionResult.emit(Resource.Success(Unit))
            else
                _submissionResult.emit(Resource.Failure(result.message ?: "error occurred"))
        } else {

            if(_setupText.value.isBlank() && _deliveryText.value.isBlank() && _categoryText.value.isBlank() && _customLanguageText.value.isBlank())
                return@launch _submissionResult.emit(Resource.Failure("Please fill the given fields"))

            val result = addJokeUseCase(
                _categoryText.value,
                deliveryText.value,
                _customLanguageText.value,
                null,
                setupText.value,
                "twopart"
            )
            if (result is Resource.Success)
                _submissionResult.emit(Resource.Success(Unit))
            else
                _submissionResult.emit(Resource.Failure(result.message ?: "error occurred"))
        }
    }

}