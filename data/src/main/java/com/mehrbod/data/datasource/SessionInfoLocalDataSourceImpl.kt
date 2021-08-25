package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question
import javax.inject.Inject

internal class SessionInfoLocalDataSourceImpl @Inject constructor(): SessionInfoLocalDataSource {

    private val answeredQuestions = ArrayList<Pair<Question, Answer?>>()
    private var isUserUsedExtraQuestion = false
    private var isUserUsedRemoveWrongAnswers = false
    private var isUserUsedExtraTime = false

    override fun addAnsweredQuestion(question: Question, answer: Answer?) {
        answeredQuestions.add(question to answer)
    }

    override fun getAllAnsweredQuestions(): List<Pair<Question, Answer?>> {
        return answeredQuestions
    }

    override fun clear() {
        answeredQuestions.clear()
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