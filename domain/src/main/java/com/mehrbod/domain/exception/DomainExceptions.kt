package com.mehrbod.domain.exception

val userUsedExtraQuestionException = RuntimeException("User already used extra question.")
val userUsedRemoveWrongAnswersException = RuntimeException("User used already remove wrong answers.")
val noQuestionsFoundException = RuntimeException("No questions where found.")