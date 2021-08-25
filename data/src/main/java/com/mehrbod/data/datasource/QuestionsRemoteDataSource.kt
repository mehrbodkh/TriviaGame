package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Question

/**
 * Although we don't want to receive any data from remote servers, this interface is here to
 * show that the data needed for the application can be fetched from the server.
 */
internal interface QuestionsRemoteDataSource {
    suspend fun getQuestions(): List<Question>
}