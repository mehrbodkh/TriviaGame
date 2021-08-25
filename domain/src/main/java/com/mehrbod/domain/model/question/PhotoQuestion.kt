package com.mehrbod.domain.model.question

data class PhotoQuestion(
    val photoUrl: String,
    override var choices: List<Answer>
): Question()
