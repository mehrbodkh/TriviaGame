package com.mehrbod.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mehrbod.data.database.entity.QuestionEntity

@Dao
internal interface QuestionsDao {
    @Query("SELECT * FROM questions_table ORDER BY RANDOM()")
    suspend fun getAllQuestions(): List<QuestionEntity>

    @Insert
    suspend fun insertQuestion(questionEntity: QuestionEntity)

    @Query("SELECT * FROM questions_table WHERE id NOT IN (:questionIds)")
    suspend fun getAnotherQuestion(questionIds: List<Int>): List<QuestionEntity>
}