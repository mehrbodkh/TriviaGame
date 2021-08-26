package com.mehrbod.domain.model.question

data class PhotoQuestion(
    val photoUrl: String,
    override val choices: List<Choice>
): Question() {
    override fun copy(choices: List<Choice>): Question {
        return this.copy(photoUrl = photoUrl, choices = choices)
    }
}
