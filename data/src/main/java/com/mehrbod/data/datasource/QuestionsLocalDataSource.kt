package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Question

internal interface QuestionsLocalDataSource {
    suspend fun getDistinctQuestions(count: Int): Result<List<Question>>
    suspend fun getAnotherQuestion(questions: List<Question>): Result<Question>
}