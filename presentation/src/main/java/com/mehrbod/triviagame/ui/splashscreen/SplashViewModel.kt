package com.mehrbod.triviagame.ui.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.triviagame.ui.splashscreen.state.SplashUIEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    companion object {
        private const val SPLASH_DELAY = 1000L
    }

    private val _uiEvents = MutableSharedFlow<SplashUIEvent>()
    val uiEvents: SharedFlow<SplashUIEvent> = _uiEvents

    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            _uiEvents.emit(SplashUIEvent.NavigateToStartScreen)
        }
    }
}