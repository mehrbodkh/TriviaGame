package com.mehrbod.domain.di

import com.mehrbod.domain.interactor.AddUserAnswerUseCase
import com.mehrbod.domain.interactor.AddUserAnswerUseCaseImpl
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
}