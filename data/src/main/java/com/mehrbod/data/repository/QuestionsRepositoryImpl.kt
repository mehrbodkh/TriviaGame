package com.mehrbod.data.repository

import com.mehrbod.data.datasource.QuestionsLocalDataSource
import com.mehrbod.data.datasource.QuestionsRemoteDataSource
import com.mehrbod.data.di.IODispatcher
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.QuestionsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class QuestionsRepositoryImpl @Inject constructor(
    /**
     * Although we don't want to receive any data from remote servers, this interface is here to
     * show that the data needed for the application can be fetched from the server.
     */
    private val questionsRemoteDataSource: QuestionsRemoteDataSource,
    private val questionsLocalDataSource: QuestionsLocalDataSource,
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher
): QuestionsRepository {

    override suspend fun getDistinctQuestions(count: Int): Result<List<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnotherQuestion(questions: List<Question>): Result<Question> {
        TODO("Not yet implemented")
    }
}