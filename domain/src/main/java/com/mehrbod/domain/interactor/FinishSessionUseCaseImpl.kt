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

        return Summery(
            result.count { it.first.choices.find { question -> question.isCorrect } == it.second },
            result.size,
        )
    }
}