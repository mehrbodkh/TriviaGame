package com.mehrbod.data.repository

import com.mehrbod.data.datasource.SessionInfoLocalDataSource
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

internal class GameSessionRepositoryImplTest {

    @MockK
    lateinit var localDataSource: SessionInfoLocalDataSource

    @InjectMockKs
    lateinit var gameSessionRepository: GameSessionRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test add answered question`() {
        every { localDataSource.addAnsweredQuestion(any(), any()) } returns Unit

        gameSessionRepository.addAnsweredQuestion(mockk(), mockk())

        verify { localDataSource.addAnsweredQuestion(any(), any()) }
    }

    @Test
    fun `test get all answered questions`() {
        every { localDataSource.getAllAnsweredQuestions() } returns mockk()

        gameSessionRepository.getAllAnsweredQuestions()

        verify { localDataSource.getAllAnsweredQuestions() }
    }

    @Test
    fun `test clear`() {
        every { localDataSource.clear() } returns Unit

        gameSessionRepository.clear()

        verify { localDataSource.clear() }
    }

    @Test
    fun `test set user used extra question`() {
        every { localDataSource.setUserUsedExtraQuestion(any()) } returns Unit

        gameSessionRepository.setUserUsedExtraQuestion(true)

        verify { localDataSource.setUserUsedExtraQuestion(true) }
    }

    @Test
    fun `test is user used extra question`() {
        every { localDataSource.isUserUsedExtraQuestion() } returns true

        gameSessionRepository.isUserUsedExtraQuestion()

        verify { localDataSource.isUserUsedExtraQuestion() }
    }

    @Test
    fun `test set user used extra time`() {
        every { localDataSource.setUserUsedExtraTime(any()) } returns Unit

        gameSessionRepository.setUserUsedExtraTime(true)

        verify { localDataSource.setUserUsedExtraTime(true) }
    }

    @Test
    fun `test is user used extra time`() {
        every { localDataSource.isUserUsedExtraTime() } returns true

        gameSessionRepository.isUserUsedExtraTime()

        verify { localDataSource.isUserUsedExtraTime() }
    }

    @Test
    fun `test set user used remove wrong answers`() {
        every { localDataSource.setUserUsedRemoveWrongAnswers(any()) } returns Unit

        gameSessionRepository.setUserUsedRemoveWrongAnswers(true)

        verify { localDataSource.setUserUsedRemoveWrongAnswers(true) }
    }

    @Test
    fun `test is user used remove wrong answers`() {
        every { localDataSource.isUserUsedRemoveWrongAnswers() } returns true

        gameSessionRepository.isUserUsedRemoveWrongAnswers()

        verify { localDataSource.isUserUsedRemoveWrongAnswers() }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}