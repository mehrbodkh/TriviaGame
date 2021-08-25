package com.mehrbod.domain.model.question

abstract class Question {
    var type: String = javaClass.name
    abstract val choices: List<Answer>

    abstract fun copy(choices: List<Answer>): Question
}