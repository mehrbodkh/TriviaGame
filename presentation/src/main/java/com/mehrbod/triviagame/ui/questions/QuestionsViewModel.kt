package com.mehrbod.triviagame.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.domain.interactor.GetQuestionsUseCase
import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.model.question.TextQuestion
import com.mehrbod.triviagame.ui.questions.state.QuestionsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase
): ViewModel() {

    private val _questionsUiState = MutableStateFlow<QuestionsUIState>(QuestionsUIState.Loading)
    val questionsUiState: StateFlow<QuestionsUIState> = _questionsUiState

    init {
        viewModelScope.launch {
            val questions = getQuestionsUseCase.getQuestions()

            questions.getOrNull()?.let {
                handleQuestions(it)
            } ?: run {

            }
        }
    }

    private fun handleQuestions(questions: List<Question>) {
        questions.forEach { question ->
            viewModelScope.launch {
                if (question is PhotoQuestion) {
                    _questionsUiState.value = QuestionsUIState.ShowPhotoQuestion(question, 10)
                } else if (question is TextQuestion) {
                    _questionsUiState.value = QuestionsUIState.ShowTextQuestion(question, 10)
                }
                delay(10000)
            }
        }
    }

}