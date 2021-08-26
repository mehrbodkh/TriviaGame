package com.mehrbod.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mehrbod.data.database.util.RuntimeTypeAdapterFactory
import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.model.question.TextQuestion

internal class DbConverter {

    @TypeConverter
    fun fromQuestion(question: Question): String {
        return when (question) {
            is TextQuestion -> {
                Gson().toJson(question)
            }
            is PhotoQuestion -> {
                Gson().toJson(question)
            }
            else -> ""
        }
    }

    @TypeConverter
    fun toQuestion(json: String): Question {
        val factory = RuntimeTypeAdapterFactory
            .of(Question::class.java, "type")
            .registerSubtype(TextQuestion::class.java, TextQuestion::class.java.name)
            .registerSubtype(PhotoQuestion::class.java, PhotoQuestion::class.java.name)

        return GsonBuilder()
            .registerTypeAdapterFactory(factory)
            .create()
            .fromJson(json, Question::class.java).apply {
                type = javaClass.name
            }
    }
}