package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.summery.Summery
import com.mehrbod.domain.repository.GameSessionRepository
import javax.inject.Inject

class FinishSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
): FinishSessionUseCase {

    override fun finish(): Summery {
        val result = gameSessionRepository.getAllAnsweredQuestions()
        gameSessionRepository.clear()

        val correctAnswers = result.count { it.first.choices.find { question -> question.isCorrect } == it.second }
        val incorrectAnswers = result.filter { it.second != null && !it.second!!.isCorrect }.size

        return Summery(
            correctAnswers,
            incorrectAnswers,
            result.size - (correctAnswers + incorrectAnswers),
            result.size,
            "$correctAnswers/${result.size}"
        )
    }
}