package com.mehrbod.data.adapter

import com.mehrbod.data.database.entity.QuestionEntity
import com.mehrbod.domain.model.question.Question

internal fun Question.convertToEntity(): QuestionEntity = QuestionEntity(question = this)