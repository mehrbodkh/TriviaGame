package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

interface GetQuestionsUseCase {
    fun getQuestions(): List<Question>
}