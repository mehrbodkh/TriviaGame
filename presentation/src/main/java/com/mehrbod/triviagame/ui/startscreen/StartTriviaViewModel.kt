package com.mehrbod.triviagame.ui.startscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.triviagame.ui.startscreen.state.StartGameUIEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class StartTriviaViewModel : ViewModel() {

    private val _uiEvent = MutableSharedFlow<StartGameUIEvent>()
    val uiEvents: SharedFlow<StartGameUIEvent> = _uiEvent

    fun onStartButtonClicked() {
        viewModelScope.launch {
            _uiEvent.emit(StartGameUIEvent.NavigateToGame)
        }
    }
}