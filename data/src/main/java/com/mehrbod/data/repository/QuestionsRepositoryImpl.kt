package com.mehrbod.data.repository

import com.mehrbod.data.datasource.QuestionsRemoteDataSource
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.QuestionsRepository
import javax.inject.Inject

internal class QuestionsRepositoryImpl @Inject constructor(
    /**
     * Although we don't want to receive any data from remote servers, this interface is here to
     * show that the data needed for the application can be fetched from the server.
     */
    private val questionsRemoteDataSource: QuestionsRemoteDataSource,

): QuestionsRepository {

    override suspend fun getDistinctQuestions(count: Int): Result<List<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnotherQuestion(questions: List<Question>): Result<Question> {
        TODO("Not yet implemented")
    }
}