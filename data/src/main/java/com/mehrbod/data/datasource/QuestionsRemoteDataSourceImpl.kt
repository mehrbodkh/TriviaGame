package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Answer
import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.Question
import com.mehrbod.domain.model.question.TextQuestion
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Although we don't want to receive any data from remote servers, this implementation is here to
 * show that the data needed for the application can be fetched from the server.
 *
 * It is been used to simulate that but with hardcoded data.
 */
internal class QuestionsRemoteDataSourceImpl @Inject constructor(): QuestionsRemoteDataSource {

    /**
     * All these data should be fetched using an api service via an object injected into the class.
     */
    override suspend fun getQuestions(): List<Question> {
        delay(1000)
        return ArrayList<Question>().apply {
            add(
                TextQuestion(
                    "What is the result of 2 + 2?",
                    listOf(
                        Answer("1", false),
                        Answer("4", true),
                        Answer("3", false),
                        Answer("5", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 3 + 4?",
                    listOf(
                        Answer("5", false),
                        Answer("6", false),
                        Answer("7", true),
                        Answer("8", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 7 + 8?",
                    listOf(
                        Answer("5", false),
                        Answer("6", false),
                        Answer("15", true),
                        Answer("8", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 1 + 9?",
                    listOf(
                        Answer("1", false),
                        Answer("2", false),
                        Answer("15", false),
                        Answer("9", true)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 8 + 8?",
                    listOf(
                        Answer("4", false),
                        Answer("8", false),
                        Answer("16", true),
                        Answer("5", false)
                    )
                )
            )
            add(
                PhotoQuestion(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1jxKbX3TkYKyu0RxVD283u-etO9p0AzOesw&usqp=CAU",
                    listOf(
                        Answer("3", true),
                        Answer("9", false),
                        Answer("0", false),
                        Answer("2", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 1 + 1?",
                    listOf(
                        Answer("2", true),
                        Answer("8", false),
                        Answer("16", false),
                        Answer("5", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 4 + 5?",
                    listOf(
                        Answer("4", false),
                        Answer("9", true),
                        Answer("16", false),
                        Answer("5", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 8 * 8?",
                    listOf(
                        Answer("12", false),
                        Answer("16", false),
                        Answer("64", true),
                        Answer("21", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 8 + 80?",
                    listOf(
                        Answer("88", true),
                        Answer("8", false),
                        Answer("16", false),
                        Answer("5", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 2 + 20?",
                    listOf(
                        Answer("22", true),
                        Answer("1", false),
                        Answer("1324", false),
                        Answer("342", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 122 / 2?",
                    listOf(
                        Answer("342", true),
                        Answer("54", false),
                        Answer("545435", false),
                        Answer("61", true)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 90 + 80?",
                    listOf(
                        Answer("88", false),
                        Answer("43243", false),
                        Answer("170", true),
                        Answer("5234", false)
                    )
                )
            )

        }
    }
}