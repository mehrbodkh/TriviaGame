package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Choice
import com.mehrbod.domain.model.question.Question
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionInfoLocalDataSourceImpl @Inject constructor(): SessionInfoLocalDataSource {

    private val answeredQuestions = ArrayList<Pair<Question, Choice?>>()
    private var isUserUsedExtraQuestion = false
    private var isUserUsedRemoveWrongAnswers = false
    private var isUserUsedExtraTime = false

    override fun addAnsweredQuestion(question: Question, answer: Choice?) {
        answeredQuestions.add(question to answer)
    }

    override fun getAllAnsweredQuestions(): List<Pair<Question, Choice?>> {
        return answeredQuestions.toList()
    }

    override fun clear() {
        answeredQuestions.clear()
        isUserUsedExtraQuestion = false
        isUserUsedRemoveWrongAnswers = false
        isUserUsedExtraTime = false
    }

    override fun setUserUsedExtraQuestion(isUserUsedExtraQuestion: Boolean) {
        this.isUserUsedExtraQuestion = isUserUsedExtraQuestion
    }

    override fun isUserUsedExtraQuestion(): Boolean {
        return this.isUserUsedExtraQuestion
    }

    override fun setUserUsedRemoveWrongAnswers(isUserUsedRemoveWrongAnswers: Boolean) {
        this.isUserUsedRemoveWrongAnswers = isUserUsedRemoveWrongAnswers
    }

    override fun isUserUsedRemoveWrongAnswers(): Boolean {
        return isUserUsedRemoveWrongAnswers
    }

    override fun setUserUsedExtraTime(isUserUsedExtraTime: Boolean) {
        this.isUserUsedExtraTime = isUserUsedExtraTime
    }

    override fun isUserUsedExtraTime(): Boolean {
        return isUserUsedExtraTime
    }
}