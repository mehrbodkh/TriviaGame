package com.mehrbod.triviagame.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.domain.interactor.GetQuestionsUseCase
import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.model.question.TextQuestion
import com.mehrbod.triviagame.ui.questions.state.QuestionsUIState
import com.mehrbod.triviagame.ui.questions.state.TimerState
import com.mehrbod.triviagame.util.startTicker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {

    private val _questionsUiState = MutableStateFlow<QuestionsUIState>(QuestionsUIState.Loading)
    val questionsUiState: StateFlow<QuestionsUIState> = _questionsUiState

    private val _timerState = MutableStateFlow<TimerState>(TimerState.Empty)
    val timerState: StateFlow<TimerState> = _timerState

    init {
        viewModelScope.launch {
            val questions = getQuestionsUseCase.getQuestions()

            questions.getOrNull()?.let {
                handleQuestions(it, 0)
            } ?: run {

            }
        }
    }

    private fun handleQuestions(questions: List<Question>, startingQuestionIndex: Int) {
        if (startingQuestionIndex >= questions.size) {
            return
        }

        val question = questions[startingQuestionIndex]

        if (question is PhotoQuestion) {
            _questionsUiState.value = QuestionsUIState.ShowPhotoQuestion(question)
        } else if (question is TextQuestion) {
            _questionsUiState.value = QuestionsUIState.ShowTextQuestion(question)
        }

        startTicker(Duration.seconds(10), Duration.seconds(1))
            .onEach { _timerState.value = TimerState.UpdateTimeLeft(it) }
            .onCompletion { handleQuestions(questions, startingQuestionIndex + 1) }
            .launchIn(viewModelScope)

    }

}