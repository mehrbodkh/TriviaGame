package com.mehrbod.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mehrbod.data.database.converter.DbConverter
import com.mehrbod.data.database.dao.QuestionsDao
import com.mehrbod.data.database.entity.QuestionEntity

@Database(entities = [QuestionEntity::class], version = 1)
@TypeConverters(DbConverter::class)
internal abstract class QuestionsDb : RoomDatabase() {

    abstract fun questionsDao(): QuestionsDao

    companion object {
        fun buildDatabase(context: Context) =
            Room
                .databaseBuilder(context, QuestionsDb::class.java, "questions.db")
                .build()
    }
}