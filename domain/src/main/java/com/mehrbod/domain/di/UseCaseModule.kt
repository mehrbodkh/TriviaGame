package com.mehrbod.domain.di

import com.mehrbod.domain.interactor.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindAddUserAnswerUseCase(
        addUserAnswerUseCaseImpl: AddUserAnswerUseCaseImpl
    ): AddUserAnswerUseCase

    @Binds
    abstract fun bindGetExtraQuestionUseCase(
        getExtraQuestionUseCaseImpl: GetExtraQuestionUseCaseImpl
    ): GetExtraQuestionUseCase

    @Binds
    abstract fun bindGetQuestionsUseCase(
        getQuestionsUseCaseImpl: GetQuestionsUseCaseImpl
    ): GetQuestionsUseCase

    @Binds
    abstract fun bindGetSummeryUseCase(
        getSummeryUseCaseImpl: GetSummeryUseCaseImpl
    ): GetSummeryUseCase

    @Binds
    abstract fun bindRemoveWrongAnswersUseCase(
        removeWrongAnswersUseCaseImpl: RemoveWrongAnswersUseCaseImpl
    ): RemoveWrongAnswersUseCase

    @Binds
    abstract fun bindFinishSessionUseCase(
        finishSessionUseCaseImpl: FinishSessionUseCaseImpl
    ): FinishSessionUseCase
}