package com.mehrbod.triviagame.ui.questions.state

sealed class ExtraTimeUIState {
    object Disable: ExtraTimeUIState()
    object Enable: ExtraTimeUIState()
}