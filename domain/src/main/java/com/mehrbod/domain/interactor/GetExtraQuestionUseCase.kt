package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

interface GetExtraQuestionUseCase {
    suspend fun getExtraQuestion(questions: List<Question>): Result<Question>
}