package com.mehrbod.domain.repository

import com.mehrbod.domain.model.question.Choice
import com.mehrbod.domain.model.question.Question

/**
 * This repository is responsible for storing and providing the information needed for each game
 * session such as answered questions and those answers.
 *
 * NOTE: This should be implemented in data layer.
 */
interface GameSessionRepository {

    /**
     * It stores the answered question. Send null answer if there were non.
     */
    fun addAnsweredQuestion(question: Question, answer: Choice?)

    /**
     * It returns all the answered questions added before. Returns empty list if nothing has beed
     * added. Returns null answer if there were non.
     */
    fun getAllAnsweredQuestions(): List<Pair<Question, Choice?>>

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

    /**
     * Sets whether the user used remove wrong answer or not.
     */
    fun setUserUsedRemoveWrongAnswers(isUserUsedRemoveWrongAnswers: Boolean)

    /**
     * Returns whether the user used remove wrong answers or not.
     */
    fun isUserUsedRemoveWrongAnswers(): Boolean

    /**
     * Sets whether the user used extra time or not.
     */
    fun setUserUsedExtraTime(isUserUsedExtraTime: Boolean)

    /**
     * Returns whether the user used extra time or not.
     */
    fun isUserUsedExtraTime(): Boolean
}