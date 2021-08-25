package com.mehrbod.domain.interactor

import com.mehrbod.domain.exception.userUsedExtraQuestionException
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.GameSessionRepository
import com.mehrbod.domain.repository.QuestionsRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class GetExtraQuestionUseCaseImplTest {

    @MockK
    lateinit var questionsRepository: QuestionsRepository

    @MockK
    lateinit var gameSessionRepository: GameSessionRepository

    @InjectMockKs
    lateinit var getExtraQuestionUseCase: GetExtraQuestionUseCaseImpl

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test extra question - successful`() = coroutineDispatcher.runBlockingTest {
        val question = mockk<Question>()
        every { gameSessionRepository.isUserUsedExtraQuestion() } returns true
        coEvery { questionsRepository.getAnotherQuestion(any()) } returns Result.success(question)

        val result = getExtraQuestionUseCase.getExtraQuestion(listOf())

        coVerify { questionsRepository.getAnotherQuestion(any()) }
        verify { gameSessionRepository.isUserUsedExtraQuestion() }
        assertEquals(question, result.getOrNull())
    }

    @Test
    fun `test extra question - failure - user used before`() = coroutineDispatcher.runBlockingTest {
        val question = mockk<Question>()
        every { gameSessionRepository.isUserUsedExtraQuestion() } returns false
        coEvery { questionsRepository.getAnotherQuestion(any()) } returns Result.success(question)

        val result = getExtraQuestionUseCase.getExtraQuestion(listOf())

        verify { gameSessionRepository.isUserUsedExtraQuestion() }
        assertTrue(result.isFailure)
        assertEquals(userUsedExtraQuestionException, result.exceptionOrNull())
    }

    @Test
    fun `test extra question - failure - no more questions`() =
        coroutineDispatcher.runBlockingTest {
            every { gameSessionRepository.isUserUsedExtraQuestion() } returns true
            coEvery { questionsRepository.getAnotherQuestion(any()) } returns Result.failure(
                Throwable("")
            )

            val result = getExtraQuestionUseCase.getExtraQuestion(listOf())

            coVerify { questionsRepository.getAnotherQuestion(any()) }
            verify { gameSessionRepository.isUserUsedExtraQuestion() }
            assertTrue(result.isFailure)
        }


    @After
    fun tearDown() {
        unmockkAll()
    }
}