package com.mehrbod.domain.model.question

data class PhotoQuestion(
    val photoUrl: String,
    override val choices: List<Answer>
): Question() {
    override fun copy(choices: List<Answer>): Question {
        return this.copy(photoUrl = photoUrl, choices = choices)
    }
}
