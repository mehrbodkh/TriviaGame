package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.GameSessionRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class AddUserAnswerUseCaseImplTest {

    @MockK
    lateinit var gameSessionRepository: GameSessionRepository

    @InjectMockKs
    lateinit var addUserAnswerUseCase: AddUserAnswerUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test add answer`() {
        val question = mockk<Question>(relaxed = true)
        val answer = mockk<Answer>(relaxed = true)
        every { gameSessionRepository.addAnsweredQuestion(any(), any()) } returns Unit

        addUserAnswerUseCase.addAnswer(question, answer)

        verify { gameSessionRepository.addAnsweredQuestion(question, answer) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}