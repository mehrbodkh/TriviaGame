package com.mehrbod.domain.interactor

import com.mehrbod.domain.exception.noQuestionsFoundException
import com.mehrbod.domain.repository.GameSessionRepository
import com.mehrbod.domain.repository.QuestionsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class GetQuestionsUseCaseImplTest {

    @MockK
    lateinit var questionsRepository: QuestionsRepository

    @InjectMockKs
    lateinit var getQuestionsUseCase: GetQuestionsUseCaseImpl

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test fetch questions - successful`() = coroutineDispatcher.runBlockingTest {
        coEvery { questionsRepository.getDistinctQuestions(10) } returns Result.success(listOf())

        val result = getQuestionsUseCase.getQuestions()

        coVerify { questionsRepository.getDistinctQuestions(10) }
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.isEmpty() ?: false)
    }

    @Test
    fun `test fetch questions - failure`() = coroutineDispatcher.runBlockingTest {
        coEvery { questionsRepository.getDistinctQuestions(10) } returns Result.failure(
            noQuestionsFoundException
        )

        val result = getQuestionsUseCase.getQuestions()

        coVerify { questionsRepository.getDistinctQuestions(10) }
        assertTrue(result.isFailure)
        assertEquals(noQuestionsFoundException, result.exceptionOrNull())
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}