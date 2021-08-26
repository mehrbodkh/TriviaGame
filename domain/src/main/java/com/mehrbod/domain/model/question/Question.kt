package com.mehrbod.domain.model.question

abstract class Question {
    var type: String = javaClass.name
    abstract val choices: List<Choice>

    abstract fun copy(choices: List<Choice>): Question
}