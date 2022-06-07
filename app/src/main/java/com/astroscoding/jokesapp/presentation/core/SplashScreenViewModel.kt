package com.astroscoding.jokesapp.presentation.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astroscoding.jokesapp.data.local.PreferencesManager
import com.astroscoding.jokesapp.presentation.core.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination = mutableStateOf(Screens.WelcomeScreen.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            preferencesManager.welcomeScreenPref.collect { hasCompleted ->
                if (hasCompleted)
                    _startDestination.value = Screens.HomeScreen.route
                else
                    _startDestination.value = Screens.WelcomeScreen.route
            }
            _isLoading.value = false
        }
    }
}
