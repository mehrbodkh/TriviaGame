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

class AddExtraTimeUseCaseImplTest {

    @MockK
    lateinit var gameSessionRepository: GameSessionRepository

    @InjectMockKs
    lateinit var addExtraTimeUseCase: AddExtraTimeUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test add extra time use case - success`() {
        every { gameSessionRepository.isUserUsedExtraTime() } returns false
        every { gameSessionRepository.setUserUsedExtraTime(any()) } returns Unit

        val result = addExtraTimeUseCase.addExtraTime()

        verify { gameSessionRepository.isUserUsedExtraTime() }
        verify { gameSessionRepository.setUserUsedExtraTime(true) }
        assertTrue(result)
    }

    @Test
    fun `test add extra time use case - failure`() {
        every { gameSessionRepository.isUserUsedExtraTime() } returns true

        val result = addExtraTimeUseCase.addExtraTime()

        verify { gameSessionRepository.isUserUsedExtraTime() }
        assertFalse(result)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}