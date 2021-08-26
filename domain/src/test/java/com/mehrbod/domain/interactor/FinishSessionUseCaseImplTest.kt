package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Choice
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.GameSessionRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class FinishSessionUseCaseImplTest {

    @MockK
    lateinit var gameSessionRepository: GameSessionRepository

    @InjectMockKs
    lateinit var finishSessionUseCase: FinishSessionUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test get empty summery`() {
        every { gameSessionRepository.getAllAnsweredQuestions() } returns listOf()
        every { gameSessionRepository.clear() } returns Unit

        val result = finishSessionUseCase.finish()

        verify { gameSessionRepository.getAllAnsweredQuestions() }
        verify { gameSessionRepository.clear() }
        assertEquals(0, result.totalQuestions)
        assertEquals(0, result.numberOfCorrectAnswers)
    }

    @Test
    fun `test get full summery - result`() {
        val question = mockk<Question>(relaxed = true)
        val question2 = mockk<Question>(relaxed = true)
        val answer = mockk<Choice>()
        val answer2 = mockk<Choice>()
        every { answer getProperty "isCorrect" } returns true
        every { answer2 getProperty "isCorrect" } returns false
        every { question getProperty "choices" } returns listOf(answer)
        every { question2 getProperty "choices" } returns listOf(answer2)
        every { gameSessionRepository.getAllAnsweredQuestions() } returns listOf(
            question to answer,
            question2 to answer2
        )
        every { gameSessionRepository.clear() } returns Unit

        val result = finishSessionUseCase.finish()

        verify { gameSessionRepository.getAllAnsweredQuestions() }
        verify { gameSessionRepository.clear() }
        assertEquals(2, result.totalQuestions)
        assertEquals(1, result.numberOfCorrectAnswers)
    }

    @Test
    fun `test get summery`() {
        val question = mockk<Question>(relaxed = true)
        val answer = mockk<Choice>()
        every { answer getProperty "isCorrect" } returns true
        every { question getProperty "choices" } returns listOf(answer)
        every { gameSessionRepository.getAllAnsweredQuestions() } returns listOf(question to answer)
        every { gameSessionRepository.clear() } returns Unit

        val result = finishSessionUseCase.finish()

        verify { gameSessionRepository.getAllAnsweredQuestions() }
        verify { gameSessionRepository.clear() }
        assertEquals(1, result.totalQuestions)
        assertEquals(1, result.numberOfCorrectAnswers)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}