package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

interface RemoveSomeWrongAnswersUseCase {

    fun removeSomeWrongAnswers(question: Question): Question
}