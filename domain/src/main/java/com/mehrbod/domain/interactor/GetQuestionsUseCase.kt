package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

/**
 * Provides all distinct questions needed for the session.
 */
interface GetQuestionsUseCase {
    suspend fun getQuestions(): Result<List<Question>>
}