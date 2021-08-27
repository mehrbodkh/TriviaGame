package com.mehrbod.triviagame.ui.questions.state

sealed class RemoveAnswersUIState {
    object Disable : RemoveAnswersUIState()
    object Enable : RemoveAnswersUIState()
}