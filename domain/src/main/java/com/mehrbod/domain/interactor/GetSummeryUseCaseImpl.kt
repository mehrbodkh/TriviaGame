package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.summery.Summery
import com.mehrbod.domain.repository.GameSessionRepository
import javax.inject.Inject

class GetSummeryUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
): GetSummeryUseCase {

    override fun getSummery(): Summery {
        val result = gameSessionRepository.getAllAnsweredQuestions()

        return Summery(
            result.count { it.first.choices.find { question -> question.isCorrect } == it.second },
            result.size,
        )
    }
}