package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.GameSessionRepository
import javax.inject.Inject

class AddUserAnswerUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : AddUserAnswerUseCase {

    override fun addAnswer(question: Question, answer: Answer) {
        gameSessionRepository.addAnsweredQuestion(question, answer)
    }
}