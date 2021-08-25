package com.mehrbod.domain.interactor

import com.mehrbod.domain.exception.userUsedRemoveWrongAnswersException
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.GameSessionRepository
import javax.inject.Inject

class RemoveWrongAnswersUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository

) : RemoveWrongAnswersUseCase {

    override fun removeWrongAnswers(question: Question): Result<Question> {
        return if (!gameSessionRepository.isUserUsedRemoveWrongAnswers()) {
            gameSessionRepository.setUserUsedRemoveWrongAnswers(true)

            val removableChoices = question.choices.filter { !it.isCorrect }.toMutableList()
            removableChoices.add(question.choices.filter { !it.isCorrect }.random())

            Result.success(question.copy(removableChoices))
        } else {
            Result.failure(userUsedRemoveWrongAnswersException)
        }
    }
}