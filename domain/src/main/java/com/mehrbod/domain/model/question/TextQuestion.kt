package com.mehrbod.domain.model.question

data class TextQuestion(
    val questionText: String,
    override var choices: List<Choice>
): Question() {

    override fun copy(choices: List<Choice>): TextQuestion {
        return  this.copy(questionText = questionText, choices = choices)
    }
}
