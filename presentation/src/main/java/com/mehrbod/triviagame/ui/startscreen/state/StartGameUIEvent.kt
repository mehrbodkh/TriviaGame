package com.mehrbod.triviagame.ui.startscreen.state

sealed class StartGameUIEvent {
    object NavigateToGame : StartGameUIEvent()
}