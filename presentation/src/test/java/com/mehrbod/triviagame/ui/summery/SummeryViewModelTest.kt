package com.mehrbod.triviagame.ui.summery

import com.mehrbod.domain.interactor.FinishSessionUseCase
import com.mehrbod.triviagame.ui.summery.state.SummeryUIEvent
import com.mehrbod.triviagame.ui.summery.state.SummeryUIState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class SummeryViewModelTest {

    @MockK
    lateinit var finishSessionUseCase: FinishSessionUseCase

    private lateinit var summeryViewModel: SummeryViewModel

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun `test fetch summery`() = coroutineDispatcher.runBlockingTest {
        every { finishSessionUseCase.finish() } returns mockk()

        summeryViewModel = SummeryViewModel(finishSessionUseCase)
        val result = summeryViewModel.uiState.first()

        verify { finishSessionUseCase.finish() }
        assertTrue(result is SummeryUIState.ShowSummery)
    }

    @Test
    fun `test replay game`() = coroutineDispatcher.runBlockingTest {
        val testResults = mutableListOf<SummeryUIEvent>()
        every { finishSessionUseCase.finish() } returns mockk()

        summeryViewModel = SummeryViewModel(finishSessionUseCase)
        val job = launch {
            summeryViewModel.uiEvent.toList(testResults)
        }
        summeryViewModel.onReplayClicked()
        job.cancel()

        assertTrue(testResults.first() is SummeryUIEvent.NavigateToStartScreen)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }
}