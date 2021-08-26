package com.mehrbod.data.datasource

import com.mehrbod.data.adapter.convertToEntity
import com.mehrbod.data.database.dao.QuestionsDao
import com.mehrbod.domain.exception.noQuestionsFoundException
import com.mehrbod.domain.model.question.Question
import javax.inject.Inject

internal class QuestionsLocalDataSourceImpl @Inject constructor(
    private val database: QuestionsDao
): QuestionsLocalDataSource {

    override suspend fun getDistinctQuestions(count: Int): Result<List<Question>> {
        val questions = database.getAllQuestions()

        return if (questions.size >= count) {
            Result.success(questions.map { it.question })
        } else {
            Result.failure(noQuestionsFoundException)
        }
    }

    override suspend fun getAnotherQuestion(questions: List<Question>): Result<Question> {
        val questionEntities = database.getAllQuestions()

        val newQuestions = questionEntities.filterNot { questions.contains(it.question) }
        return if (newQuestions.isNotEmpty()) {
            Result.success(newQuestions.random().question)
        } else {
            Result.failure(noQuestionsFoundException)
        }
    }

    override suspend fun addQuestions(questions: List<Question>) {
        questions.forEach {
            database.insertQuestion(it.convertToEntity())
        }
    }
}