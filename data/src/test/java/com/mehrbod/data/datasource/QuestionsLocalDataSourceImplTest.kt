package com.mehrbod.data.datasource

import com.mehrbod.data.adapter.convertToEntity
import com.mehrbod.data.database.dao.QuestionsDao
import com.mehrbod.data.database.entity.QuestionEntity
import com.mehrbod.domain.exception.noQuestionsFoundException
import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.model.question.TextQuestion
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
internal class QuestionsLocalDataSourceImplTest {

    @MockK
    lateinit var database: QuestionsDao

    @InjectMockKs
    lateinit var questionsLocalDataSource: QuestionsLocalDataSourceImpl

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getting all questions - empty database`() = coroutineDispatcher.runBlockingTest {
        coEvery { database.getAllQuestions() } returns listOf()

        val result = questionsLocalDataSource.getDistinctQuestions(10)

        coVerify { database.getAllQuestions() }
        assertEquals(noQuestionsFoundException, result.exceptionOrNull())
    }

    @Test
    fun `test getting all questions - full database`() = coroutineDispatcher.runBlockingTest {
        val questionEntity = mockk<QuestionEntity>()
        every { questionEntity.question } returns mockk()
        coEvery { database.getAllQuestions() } returns listOf(questionEntity)

        val result = questionsLocalDataSource.getDistinctQuestions(1)

        coVerify { database.getAllQuestions() }
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
    }

    @Test
    fun `test add question`() = coroutineDispatcher.runBlockingTest {
        coEvery { database.insertQuestion(any()) } returns Unit

        questionsLocalDataSource.addQuestions(listOf(mockk(), mockk()))

        coVerify(exactly = 2) { database.insertQuestion(any()) }
    }

    @Test
    fun `test get another question - success`() = coroutineDispatcher.runBlockingTest {
        val question = mockk<Question>(relaxed = true)
        coEvery { database.getAllQuestions() } returns listOf(
            TextQuestion(
                "1",
                listOf(Answer("1", false))
            ).convertToEntity(),
            TextQuestion(
                "2",
                listOf(Answer("2", false))
            ).convertToEntity(),
            TextQuestion(
                "3",
                listOf(Answer("3", false))
            ).convertToEntity()
        )

        val result = questionsLocalDataSource.getAnotherQuestion(
            listOf(
                TextQuestion(
                    "1",
                    listOf(Answer("1", false))
                ),
                TextQuestion(
                    "2",
                    listOf(Answer("2", false))
                ),
            )
        )

        assertTrue(result.isSuccess)
        assertEquals(
            TextQuestion(
                "3",
                listOf(Answer("3", false))
            ), result.getOrNull()
        )
    }

    @Test
    fun `test get another question - failure`() = coroutineDispatcher.runBlockingTest {
        coEvery { database.getAllQuestions() } returns listOf(
            TextQuestion(
                "1",
                listOf(Answer("1", false))
            ).convertToEntity(),
            TextQuestion(
                "2",
                listOf(Answer("2", false))
            ).convertToEntity()
        )

        val result = questionsLocalDataSource.getAnotherQuestion(
            listOf(
                TextQuestion(
                    "1",
                    listOf(Answer("1", false))
                ),
                TextQuestion(
                    "2",
                    listOf(Answer("2", false))
                ),
            )
        )

        assertTrue(result.isFailure)
        assertEquals(noQuestionsFoundException, result.exceptionOrNull())
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}