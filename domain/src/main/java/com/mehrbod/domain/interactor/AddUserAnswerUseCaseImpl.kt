package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Choice
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.GameSessionRepository
import javax.inject.Inject

class AddUserAnswerUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : AddUserAnswerUseCase {

    override fun addAnswer(question: Question, answer: Choice?) {
        gameSessionRepository.addAnsweredQuestion(question, answer)
    }
}