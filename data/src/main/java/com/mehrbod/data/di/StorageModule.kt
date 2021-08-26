package com.mehrbod.data.di

import android.content.Context
import com.mehrbod.data.database.QuestionsDb
import com.mehrbod.data.database.dao.QuestionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class StorageModule {

    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): QuestionsDao {
        return QuestionsDb.buildDatabase(context).questionsDao()
    }
}