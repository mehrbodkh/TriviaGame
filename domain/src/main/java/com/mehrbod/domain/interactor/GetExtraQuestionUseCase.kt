package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

interface GetExtraQuestionUseCase {
    fun getExtraQuestion(): Question
}