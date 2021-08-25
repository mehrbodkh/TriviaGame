package com.mehrbod.data.di

import com.mehrbod.data.datasource.QuestionsRemoteDataSource
import com.mehrbod.data.datasource.QuestionsRemoteDataSourceImpl
import com.mehrbod.data.datasource.SessionInfoLocalDataSource
import com.mehrbod.data.datasource.SessionInfoLocalDataSourceImpl
import com.mehrbod.data.repository.GameSessionRepositoryImpl
import com.mehrbod.data.repository.QuestionsRepositoryImpl
import com.mehrbod.domain.repository.GameSessionRepository
import com.mehrbod.domain.repository.QuestionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindSessionInfoLocalDataSource(
        sessionInfoLocalDataSourceImpl: SessionInfoLocalDataSourceImpl
    ): SessionInfoLocalDataSource

    @Binds
    abstract fun bindQuestionsRemoteDataSource(
        questionsRemoteDataSourceImpl: QuestionsRemoteDataSourceImpl
    ): QuestionsRemoteDataSource

    @Binds
    abstract fun bindGameSessionRepository(
        gameSessionRepositoryImpl: GameSessionRepositoryImpl
    ): GameSessionRepository

    @Binds
    abstract fun bindQuestionsRepository(
        questionsRepositoryImpl: QuestionsRepositoryImpl
    ): QuestionsRepository
}