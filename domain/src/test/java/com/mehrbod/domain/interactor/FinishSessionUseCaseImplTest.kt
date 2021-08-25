package com.mehrbod.domain.interactor

import com.mehrbod.domain.repository.GameSessionRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
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
    fun `test finish session`() {
        every { gameSessionRepository.clear() } returns Unit

        finishSessionUseCase.finishSession()

        verify { gameSessionRepository.clear() }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}