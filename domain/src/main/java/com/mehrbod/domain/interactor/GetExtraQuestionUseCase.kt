package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question

/**
 * Checks whether the user can use extra question or not. Supposes that by calling this, user used
 * the ability.
 */
interface GetExtraQuestionUseCase {
    /**
     * Returns success if a question could be provided.
     * Returns failure if the user used the ability already or no more questions are available.
     */
    suspend fun getExtraQuestion(questions: List<Question>): Result<Question>
}