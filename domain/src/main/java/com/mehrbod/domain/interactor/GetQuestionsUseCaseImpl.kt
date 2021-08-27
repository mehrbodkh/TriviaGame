package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.repository.QuestionsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetQuestionsUseCaseImpl @Inject constructor(
    private val questionsRepository: QuestionsRepository
) : GetQuestionsUseCase {

    companion object {
        private const val NUMBER_OF_QUESTIONS = 10
    }

    override suspend fun getQuestions(): Result<List<Question>> {
        return questionsRepository.getDistinctQuestions(NUMBER_OF_QUESTIONS)
    }
}