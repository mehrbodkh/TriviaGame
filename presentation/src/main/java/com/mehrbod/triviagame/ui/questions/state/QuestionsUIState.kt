package com.mehrbod.triviagame.ui.questions.state

import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.TextQuestion

sealed class QuestionsUIState {
    object Loading : QuestionsUIState()
    class ShowErrorState(val message: String) : QuestionsUIState()
    class ShowTextQuestion(val question: TextQuestion) : QuestionsUIState()
    class ShowPhotoQuestion(val question: PhotoQuestion) : QuestionsUIState()
}
