package com.mehrbod.data.repository

import com.mehrbod.data.datasource.SessionInfoLocalDataSource
import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.GameSessionRepository
import javax.inject.Inject

internal class GameSessionRepositoryImpl @Inject constructor(
    private val sessionInfoLocalDataSource: SessionInfoLocalDataSource
): GameSessionRepository {

    override fun addAnsweredQuestion(question: Question, answer: Answer?) {
        sessionInfoLocalDataSource.addAnsweredQuestion(question, answer)
    }

    override fun getAllAnsweredQuestions(): List<Pair<Question, Answer?>> {
        return sessionInfoLocalDataSource.getAllAnsweredQuestions()
    }

    override fun clear() {
        sessionInfoLocalDataSource.clear()
    }

    override fun setUserUsedExtraQuestion(isUserUsedExtraQuestion: Boolean) {
        sessionInfoLocalDataSource.setUserUsedExtraQuestion(isUserUsedExtraQuestion)
    }

    override fun isUserUsedExtraQuestion(): Boolean {
        return sessionInfoLocalDataSource.isUserUsedExtraQuestion()
    }

    override fun setUserUsedRemoveWrongAnswers(isUserUsedRemoveWrongAnswers: Boolean) {
        sessionInfoLocalDataSource.setUserUsedRemoveWrongAnswers(isUserUsedRemoveWrongAnswers)
    }

    override fun isUserUsedRemoveWrongAnswers(): Boolean {
        return sessionInfoLocalDataSource.isUserUsedRemoveWrongAnswers()
    }

    override fun setUserUsedExtraTime(isUserUsedExtraTime: Boolean) {
        sessionInfoLocalDataSource.setUserUsedExtraTime(isUserUsedExtraTime)
    }

    override fun isUserUsedExtraTime(): Boolean {
        return sessionInfoLocalDataSource.isUserUsedExtraTime()
    }
}