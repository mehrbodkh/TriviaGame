package com.mehrbod.domain.model.summery

data class Summery(
    val numberOfCorrectAnswers: Int,
    val numberOfIncorrectAnswers: Int,
    val numberOfUnansweredQuestions: Int,
    val totalQuestions: Int,
    val score: String
)
