package com.mehrbod.domain.model.question

abstract class Question {
    abstract val choices: List<Answer>

    abstract fun copy(choices: List<Answer>): Question
}