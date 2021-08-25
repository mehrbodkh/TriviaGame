package com.mehrbod.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mehrbod.domain.model.question.Question

@Entity(tableName = "questions_table")
internal data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val questions: Question
)