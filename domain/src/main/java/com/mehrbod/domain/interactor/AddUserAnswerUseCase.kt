package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question

interface AddUserAnswerUseCase {
    fun addAnswer(question: Question, answer: Answer)
}