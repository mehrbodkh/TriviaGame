package com.mehrbod.data.datasource

import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class SessionInfoLocalDataSourceImplTest {

    private val sessionInfoLocalDataSource = SessionInfoLocalDataSourceImpl()

    @Before
    fun setUp() {
        sessionInfoLocalDataSource.clear()
    }

    @Test
    fun `test add answered question`() {
        sessionInfoLocalDataSource.addAnsweredQuestion(mockk(), mockk())

        val result = sessionInfoLocalDataSource.getAllAnsweredQuestions()

        assertEquals(1, result.size)
    }

    @Test
    fun `test clear`() {
        sessionInfoLocalDataSource.addAnsweredQuestion(mockk(), mockk())
        sessionInfoLocalDataSource.clear()

        val result = sessionInfoLocalDataSource.getAllAnsweredQuestions()

        assertEquals(0, result.size)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}