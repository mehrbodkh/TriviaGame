package com.mehrbod.triviagame.ui.questions.state

sealed class AnotherQuestionUIState {
    object Disable : AnotherQuestionUIState()
    object Enable : AnotherQuestionUIState()
}