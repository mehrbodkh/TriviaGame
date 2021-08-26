package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Choice
import com.mehrbod.domain.model.question.Question

internal interface SessionInfoLocalDataSource {
    fun addAnsweredQuestion(question: Question, answer: Choice?)
    fun getAllAnsweredQuestions(): List<Pair<Question, Choice?>>
    fun clear()
    fun setUserUsedExtraQuestion(isUserUsedExtraQuestion: Boolean)
    fun isUserUsedExtraQuestion(): Boolean
    fun setUserUsedRemoveWrongAnswers(isUserUsedRemoveWrongAnswers: Boolean)
    fun isUserUsedRemoveWrongAnswers(): Boolean
    fun setUserUsedExtraTime(isUserUsedExtraTime: Boolean)
    fun isUserUsedExtraTime(): Boolean
}