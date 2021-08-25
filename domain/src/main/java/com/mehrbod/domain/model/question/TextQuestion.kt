package com.mehrbod.domain.model.question

data class TextQuestion(
    val questionText: String,
    override var choices: List<Answer>
): Question() {

    override fun copy(choices: List<Answer>): TextQuestion {
        return  this.copy(questionText = questionText, choices = choices)
    }
}
