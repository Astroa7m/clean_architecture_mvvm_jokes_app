package com.astroscoding.jokesapp.presentation.screen.on_boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astroscoding.jokesapp.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel(){

    fun setCompleted(){
        viewModelScope.launch(Dispatchers.IO) {
            preferencesManager.setWelcomeScreenState()
        }
    }

}