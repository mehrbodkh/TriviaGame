package com.mehrbod.domain.interactor

import com.mehrbod.domain.exception.userUsedRemoveWrongAnswersException
import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.model.question.TextQuestion
import com.mehrbod.domain.repository.GameSessionRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RemoveWrongAnswersUseCaseImplTest {

    @MockK
    lateinit var gameSessionRepository: GameSessionRepository

    @InjectMockKs
    lateinit var removeWrongAnswersUseCase: RemoveWrongAnswersUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test remove answers - successful`() {
        val question = mockk<TextQuestion>()
        every { question getProperty "questionText" } returns "something"
        every { question getProperty "choices" } returns listOf(
            Answer("1", false),
            Answer("2", false),
            Answer("3", true),
            Answer("4", false)
        )
        every { (question as Question).copy(any()) } returns question
        every { gameSessionRepository.isUserUsedRemoveWrongAnswers() } returns false
        every { gameSessionRepository.setUserUsedRemoveWrongAnswers(true) } returns Unit

        val result = removeWrongAnswersUseCase.removeWrongAnswers(question)

        verify { gameSessionRepository.isUserUsedRemoveWrongAnswers() }
        verify { gameSessionRepository.setUserUsedRemoveWrongAnswers(true) }
        assertTrue(result.isSuccess)
        assertEquals(4, (result.getOrNull() as? TextQuestion)?.choices?.size)
        assertNotNull(result.getOrNull()?.choices?.find { it.isCorrect })
    }

    @Test
    fun `test remove answers - failure`() {
        val question = mockk<TextQuestion>()
        every { question getProperty "questionText" } returns "something"
        every { question getProperty "choices" } returns listOf(
            Answer("1", false),
            Answer("2", false),
            Answer("3", true),
            Answer("4", false)
        )
        every { (question as Question).copy(any()) } returns question
        every { gameSessionRepository.isUserUsedRemoveWrongAnswers() } returns true
        every { gameSessionRepository.setUserUsedRemoveWrongAnswers(true) } returns Unit

        val result = removeWrongAnswersUseCase.removeWrongAnswers(question)

        verify { gameSessionRepository.isUserUsedRemoveWrongAnswers() }
        assertTrue(result.isFailure)
        assertEquals(userUsedRemoveWrongAnswersException, result.exceptionOrNull())
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}