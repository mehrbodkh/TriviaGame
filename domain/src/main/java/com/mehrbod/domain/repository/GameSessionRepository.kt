package com.mehrbod.domain.repository

import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question

/**
 * This repository is responsible for storing and providing the information needed for each game
 * session such as answered questions and those answers.
 *
 * NOTE: This should be implemented in data layer.
 */
interface GameSessionRepository {

    /**
     * It stores the answered question.
     */
    fun addAnsweredQuestion(question: Question, answer: Answer)

    /**
     * It returns all the answered questions added before. Returns empty list if nothing has beed
     * added.
     */
    fun getAllAnsweredQuestions(): List<Pair<Question, Answer>>

    /**
     * Clears all the already added answers and questions related to them.
     */
    fun clear()

    /**
     * Sets whether the user used extra question or not.
     */
    fun setUserUsedExtraQuestion(isUserUsedExtraQuestion: Boolean)

    /**
     * Returns whether the user used extra question or not.
     */
    fun isUserUsedExtraQuestion(): Boolean
}