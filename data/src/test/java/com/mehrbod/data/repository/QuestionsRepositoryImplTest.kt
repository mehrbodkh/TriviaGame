package com.mehrbod.data.repository

import com.mehrbod.data.datasource.QuestionsLocalDataSource
import com.mehrbod.data.datasource.QuestionsRemoteDataSource
import com.mehrbod.data.util.couldNotFetchQuestions
import com.mehrbod.domain.repository.QuestionsRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class QuestionsRepositoryImplTest {

    @MockK
    lateinit var localDataSource: QuestionsLocalDataSource

    @MockK
    lateinit var remoteDataSource: QuestionsRemoteDataSource

    private lateinit var questionsRepository: QuestionsRepository

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        questionsRepository =
            QuestionsRepositoryImpl(remoteDataSource, localDataSource, coroutineDispatcher)
    }

    @Test
    fun `test fetch questions - local source`() = coroutineDispatcher.runBlockingTest {
        coEvery { localDataSource.getDistinctQuestions(any()) } returns Result.success(listOf())

        questionsRepository.getDistinctQuestions(10)

        coVerify { localDataSource.getDistinctQuestions(10) }
    }

    @Test
    fun `test fetch questions - remote source`() = coroutineDispatcher.runBlockingTest {
        coEvery { localDataSource.getDistinctQuestions(any()) } returns Result.failure(Throwable())
        coEvery { remoteDataSource.getQuestions() } returns listOf()

        questionsRepository.getDistinctQuestions(10)

        coVerify { localDataSource.getDistinctQuestions(10) }
        coVerify { remoteDataSource.getQuestions() }
        coVerify { localDataSource.addQuestions(any()) }
    }

    @Test
    fun `test fetch questions - failure`() = coroutineDispatcher.runBlockingTest {
        coEvery { localDataSource.getDistinctQuestions(any()) } returns Result.failure(Throwable())
        coEvery { remoteDataSource.getQuestions() } throws Exception()

        val result = questionsRepository.getDistinctQuestions(10)

        coVerify { localDataSource.getDistinctQuestions(10) }
        coVerify { remoteDataSource.getQuestions() }
        assertEquals(couldNotFetchQuestions, result.exceptionOrNull())
    }

    @Test
    fun `test get another question`() = coroutineDispatcher.runBlockingTest {
        coEvery { localDataSource.getAnotherQuestion(any()) } returns Result.success(mockk())

        questionsRepository.getAnotherQuestion(listOf())

        coVerify { localDataSource.getAnotherQuestion(any()) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}