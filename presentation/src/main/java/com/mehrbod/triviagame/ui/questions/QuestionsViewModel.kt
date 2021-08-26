package com.mehrbod.triviagame.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.domain.interactor.AddUserAnswerUseCase
import com.mehrbod.domain.interactor.GetQuestionsUseCase
import com.mehrbod.domain.model.question.Choice
import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.model.question.TextQuestion
import com.mehrbod.triviagame.ui.questions.state.QuestionsUIState
import com.mehrbod.triviagame.ui.questions.state.TimerState
import com.mehrbod.triviagame.util.startTicker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val addUserAnswerUseCase: AddUserAnswerUseCase
) : ViewModel() {

    private val _questionsUiState = MutableStateFlow<QuestionsUIState>(QuestionsUIState.Loading)
    val questionsUiState: StateFlow<QuestionsUIState> = _questionsUiState

    private val _timerState = MutableStateFlow<TimerState>(TimerState.Empty)
    val timerState: StateFlow<TimerState> = _timerState

    private var timerJob: Job? = null
    private var questions: List<Question>? = null
    private var currentQuestionIndex = 0

    init {
        viewModelScope.launch {
            val questions = getQuestionsUseCase.getQuestions()

            questions.getOrNull()?.let {
                this@QuestionsViewModel.questions = it
                handleQuestions()
            } ?: run {

            }
        }
    }

    private fun handleQuestions() {
        questions?.let { questions ->
            if (currentQuestionIndex >= questions.size) {
                return
            }

            val question = questions[currentQuestionIndex]

            if (question is PhotoQuestion) {
                _questionsUiState.value = QuestionsUIState.ShowPhotoQuestion(question)
            } else if (question is TextQuestion) {
                _questionsUiState.value = QuestionsUIState.ShowTextQuestion(question)
            }

            timerJob = startTicker(Duration.seconds(10), Duration.seconds(1))
                .onEach { _timerState.value = TimerState.UpdateTimeLeft(it, Duration.seconds(10)) }
                .onCompletion {
                    currentQuestionIndex++
                    handleQuestions()
                }
                .launchIn(viewModelScope)
        }
    }

    fun onChoiceClicked(choice: Choice) {
        questions?.let { questions ->
            addUserAnswerUseCase.addAnswer(questions[currentQuestionIndex], choice)
            timerJob?.cancel()
        }
    }

    fun onTimeAbilityClicked() {

    }

    fun onRemoveWrongAnswersAbilityClicked() {

    }

    fun onAnotherQuestionAbilityClicked() {

    }

}