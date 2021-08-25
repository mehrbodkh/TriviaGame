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

            val newChoices = question.choices.filter { it.isCorrect }.toMutableList()
            newChoices.add(question.choices.filter { !it.isCorrect }.random())

            Result.success(question.copy(newChoices))
        } else {
            Result.failure(userUsedRemoveWrongAnswersException)
        }
    }
}