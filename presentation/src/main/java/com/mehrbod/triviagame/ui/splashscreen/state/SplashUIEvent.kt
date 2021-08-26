package com.mehrbod.triviagame.ui.splashscreen.state

sealed class SplashUIEvent {
    object NavigateToStartScreen : SplashUIEvent()
}