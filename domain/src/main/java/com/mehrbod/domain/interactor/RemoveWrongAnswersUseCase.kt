package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

interface RemoveWrongAnswersUseCase {
    fun remoteWrongAnswers(question: Question): Question
}