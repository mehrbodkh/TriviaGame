package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question

internal interface SessionInfoLocalDataSource {
    fun addAnsweredQuestion(question: Question, answer: Answer?)
    fun getAllAnsweredQuestions(): List<Pair<Question, Answer?>>
    fun clear()
    fun setUserUsedExtraQuestion(isUserUsedExtraQuestion: Boolean)
    fun isUserUsedExtraQuestion(): Boolean
    fun setUserUsedRemoveWrongAnswers(isUserUsedRemoveWrongAnswers: Boolean)
    fun isUserUsedRemoveWrongAnswers(): Boolean
    fun setUserUsedExtraTime(isUserUsedExtraTime: Boolean)
    fun isUserUsedExtraTime(): Boolean
}