package com.mehrbod.triviagame.ui.questions

import com.mehrbod.domain.interactor.*
import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.TextQuestion
import com.mehrbod.triviagame.ui.questions.state.QuestionsUIState
import com.mehrbod.triviagame.ui.questions.state.TimerState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

/**
 * Less test has been written for this class as a mean to reduce project completion time.
 */
@ExperimentalTime
@ExperimentalCoroutinesApi
class QuestionsViewModelTest {

    @MockK
    lateinit var getQuestionsUseCase: GetQuestionsUseCase

    @MockK
    lateinit var addUserAnswersUseCase: AddUserAnswerUseCase

    @MockK
    lateinit var addExtraTimeUseCase: AddExtraTimeUseCase

    @MockK
    lateinit var removeWrongAnswersUseCase: RemoveWrongAnswersUseCase

    @MockK
    lateinit var getExtraQuestionUseCase: GetExtraQuestionUseCase

    lateinit var viewModel: QuestionsViewModel

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun `test initial state`() = coroutineDispatcher.runBlockingTest {
        coEvery { getQuestionsUseCase.getQuestions() } returns Result.success(listOf(mockk<PhotoQuestion>()))

        viewModel = QuestionsViewModel(
            getQuestionsUseCase,
            addUserAnswersUseCase,
            addExtraTimeUseCase,
            removeWrongAnswersUseCase,
            getExtraQuestionUseCase
        )

        var result = viewModel.questionsUiState.first()

        coVerify { getQuestionsUseCase.getQuestions() }
        assertTrue(result is QuestionsUIState.ShowPhotoQuestion)

        coEvery { getQuestionsUseCase.getQuestions() } returns Result.success(listOf(mockk<TextQuestion>()))

        viewModel = QuestionsViewModel(
            getQuestionsUseCase,
            addUserAnswersUseCase,
            addExtraTimeUseCase,
            removeWrongAnswersUseCase,
            getExtraQuestionUseCase
        )

        result = viewModel.questionsUiState.first()

        coVerify { getQuestionsUseCase.getQuestions() }
        assertTrue(result is QuestionsUIState.ShowTextQuestion)
    }

    @Test
    fun `test timer`() = coroutineDispatcher.runBlockingTest {
        coEvery { getQuestionsUseCase.getQuestions() } returns Result.success(listOf(mockk<PhotoQuestion>()))

        viewModel = QuestionsViewModel(
            getQuestionsUseCase,
            addUserAnswersUseCase,
            addExtraTimeUseCase,
            removeWrongAnswersUseCase,
            getExtraQuestionUseCase
        )

        val result = viewModel.timerState.first()

        assertTrue(result is TimerState.UpdateTimeLeft)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }
}