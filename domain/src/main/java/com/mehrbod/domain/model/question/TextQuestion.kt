package com.mehrbod.domain.model.question

data class TextQuestion(
    val questionText: String,
    override var choices: List<Answer>
): Question()