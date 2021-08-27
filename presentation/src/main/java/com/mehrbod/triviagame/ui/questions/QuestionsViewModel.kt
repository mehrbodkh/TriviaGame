package com.mehrbod.triviagame.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.domain.interactor.*
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
    private val addUserAnswerUseCase: AddUserAnswerUseCase,
    private val addExtraTimeUseCase: AddExtraTimeUseCase,
    private val removeWrongAnswersUseCase: RemoveWrongAnswersUseCase,
    private val getExtraQuestionUseCase: GetExtraQuestionUseCase
) : ViewModel() {

    private val _questionsUiState = MutableStateFlow<QuestionsUIState>(QuestionsUIState.Loading)
    val questionsUiState: StateFlow<QuestionsUIState> = _questionsUiState

    private val _timerState = MutableStateFlow<TimerState>(TimerState.Empty)
    val timerState: StateFlow<TimerState> = _timerState

    private var timerJob: Job? = null
    private var questions: List<Question>? = null
    private var currentQuestionIndex = 0

    private var isTimeAbilityChosen = false
    private var isAnotherQuestionAbilityChosen = false
    private var extraQuestion: Question? = null

    init {
        viewModelScope.launch {
            val questions = getQuestionsUseCase.getQuestions()

            questions.getOrNull()?.let {
                this@QuestionsViewModel.questions = it
                handleCurrentQuestions()
            } ?: run {

            }
        }
    }

    private fun handleCurrentQuestions() {
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
                    handleQuestionJobCompleted()
                }
                .launchIn(viewModelScope)
        }
    }

    private fun handleQuestionJobCompleted() {
        if (timerJob?.isCancelled == true) {
            when {
                isTimeAbilityChosen -> {
                    handleExtraTimeAbility()
                }
                isAnotherQuestionAbilityChosen -> {
                    handleAnotherQuestionAbility()
                }
                else -> {
                    goToNextQuestion()
                }
            }

        } else {
            goToNextQuestion()
        }
    }

    private fun handleExtraTimeAbility() {
        isTimeAbilityChosen = false
        timerJob = startTicker(
            (_timerState.value as TimerState.UpdateTimeLeft).time + Duration.seconds(10),
            Duration.seconds(1)
        )
            .onEach {
                _timerState.value = TimerState.UpdateTimeLeft(it, Duration.seconds(20))
            }
            .onCompletion {
                handleQuestionJobCompleted()
            }
            .launchIn(viewModelScope)
    }

    private fun handleAnotherQuestionAbility() {
        isAnotherQuestionAbilityChosen = false

        val newQuestions = questions?.toMutableList()
        extraQuestion?.let { extraQuestion ->
            newQuestions?.removeAt(currentQuestionIndex)
            newQuestions?.add(currentQuestionIndex, extraQuestion)
            questions = newQuestions
        }

        handleCurrentQuestions()
    }

    private fun goToNextQuestion() {
        currentQuestionIndex++
        handleCurrentQuestions()
    }

    fun onChoiceClicked(choice: Choice) {
        questions?.let { questions ->
            addUserAnswerUseCase.addAnswer(questions[currentQuestionIndex], choice)
            timerJob?.cancel()
        }
    }

    fun onTimeAbilityClicked() {
        if (addExtraTimeUseCase.addExtraTime()) {
            isTimeAbilityChosen = true
            timerJob?.cancel()
        }
    }

    fun onRemoveWrongAnswersAbilityClicked() {
        questions?.let { questions ->
            val question = removeWrongAnswersUseCase.removeWrongAnswers(questions[currentQuestionIndex])

            question.getOrNull()?.let { question ->
                if (question is PhotoQuestion) {
                    _questionsUiState.value = QuestionsUIState.ShowPhotoQuestion(question)
                } else if (question is TextQuestion) {
                    _questionsUiState.value = QuestionsUIState.ShowTextQuestion(question)
                }
            }
        }
    }

    fun onAnotherQuestionAbilityClicked() {
        viewModelScope.launch {
            questions?.let { questions ->
                val newQuestion = getExtraQuestionUseCase.getExtraQuestion(questions)

                newQuestion.getOrNull()?.let {
                    extraQuestion = it
                    isAnotherQuestionAbilityChosen = true
                    timerJob?.cancel()
                }
            }
        }
    }

}