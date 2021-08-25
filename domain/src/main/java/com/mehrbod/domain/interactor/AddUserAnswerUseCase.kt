package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question

/**
 * Adds the answer given by the user for the given question. NULL answer in case that user didn't
 * provide any answers.
 */
interface AddUserAnswerUseCase {
    fun addAnswer(question: Question, answer: Answer?)
}