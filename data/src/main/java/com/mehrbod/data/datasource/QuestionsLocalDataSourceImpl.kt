package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Question

internal class QuestionsLocalDataSourceImpl : QuestionsLocalDataSource {
    override suspend fun getDistinctQuestions(count: Int): Result<List<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnotherQuestion(questions: List<Question>): Result<Question> {
        TODO("Not yet implemented")
    }
}