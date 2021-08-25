package com.mehrbod.domain.interactor

import com.mehrbod.domain.exception.userUsedExtraQuestionException
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.GameSessionRepository
import com.mehrbod.domain.repository.QuestionsRepository
import javax.inject.Inject

class GetExtraQuestionUseCaseImpl @Inject constructor(
    private val questionsRepository: QuestionsRepository,
    private val gameSessionRepository: GameSessionRepository
): GetExtraQuestionUseCase {

    override suspend fun getExtraQuestion(questions: List<Question>): Result<Question> {
        return if (!gameSessionRepository.isUserUsedExtraQuestion()) {
            gameSessionRepository.setUserUsedExtraQuestion(true)
            questionsRepository.getAnotherQuestion(questions)
        } else {
            Result.failure(userUsedExtraQuestionException)
        }
    }
}