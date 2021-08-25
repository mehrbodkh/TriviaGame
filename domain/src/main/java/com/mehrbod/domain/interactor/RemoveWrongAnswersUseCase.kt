package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

/**
 * Removes 2 of the wrong answers if possible.
 */
interface RemoveWrongAnswersUseCase {
    /**
     * Returns success if the ability is available.
     * Returns failure if user used the ability already.
     */
    fun removeWrongAnswers(question: Question): Result<Question>
}