package com.mehrbod.domain.repository

import com.mehrbod.domain.model.question.Question

/**
 * This interface is responsible for providing questions needed from the local or remote data
 * sources.
 *
 * NOTE: This should be implemented in data layer.
 */
interface QuestionsRepository {

    /**
     * This method is gonna be returning distinct questions with the amount of count.
     *
     * It returns success if the questions are available.
     * It returns failure if the questions are not available or the count exceeds the number of
     * available questions.
     *
     * @param count questions amount needed
     * @return result of list of questions
     */
    suspend fun getDistinctQuestions(count: Int): Result<List<Question>>

    /**
     * Returns one question which is not among the passed questions. Returns failure if there are
     * no remaining questions.
     */
    suspend fun getAnotherQuestion(questions: List<Question>): Result<Question>
}