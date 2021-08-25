package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

interface GetQuestionsUseCase {
    suspend fun getQuestions(): Result<List<Question>>
}