package com.mehrbod.data.datasource

import com.mehrbod.domain.model.question.Choice
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
                        Choice("1", false),
                        Choice("4", true),
                        Choice("3", false),
                        Choice("5", false)
                    )
                )
            )
            add(
                PhotoQuestion(
                    "https://cgmood.com/storage/previews/05-2020/20093/20093.jpg",
                    listOf(
                        Choice("3 balls", true),
                        Choice("9 balls", false),
                        Choice("1 ball", false),
                        Choice("2 balls", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 3 + 4?",
                    listOf(
                        Choice("5", false),
                        Choice("6", false),
                        Choice("7", true),
                        Choice("8", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 7 + 8?",
                    listOf(
                        Choice("5", false),
                        Choice("6", false),
                        Choice("15", true),
                        Choice("8", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 1 + 9?",
                    listOf(
                        Choice("1", false),
                        Choice("2", false),
                        Choice("15", false),
                        Choice("10", true)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 8 + 8?",
                    listOf(
                        Choice("4", false),
                        Choice("8", false),
                        Choice("16", true),
                        Choice("5", false)
                    )
                )
            )
            add(
                PhotoQuestion(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1jxKbX3TkYKyu0RxVD283u-etO9p0AzOesw&usqp=CAU",
                    listOf(
                        Choice("3", true),
                        Choice("9", false),
                        Choice("0", false),
                        Choice("2", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 1 + 1?",
                    listOf(
                        Choice("2", true),
                        Choice("8", false),
                        Choice("16", false),
                        Choice("5", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 4 + 5?",
                    listOf(
                        Choice("4", false),
                        Choice("9", true),
                        Choice("16", false),
                        Choice("5", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 8 * 8?",
                    listOf(
                        Choice("12", false),
                        Choice("16", false),
                        Choice("64", true),
                        Choice("21", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 8 + 80?",
                    listOf(
                        Choice("88", true),
                        Choice("8", false),
                        Choice("16", false),
                        Choice("5", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 2 + 20?",
                    listOf(
                        Choice("22", true),
                        Choice("1", false),
                        Choice("1324", false),
                        Choice("342", false)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 122 / 2?",
                    listOf(
                        Choice("342", false),
                        Choice("54", false),
                        Choice("545435", false),
                        Choice("61", true)
                    )
                )
            )
            add(
                TextQuestion(
                    "What is the result of 90 + 80?",
                    listOf(
                        Choice("88", false),
                        Choice("43243", false),
                        Choice("170", true),
                        Choice("5234", false)
                    )
                )
            )

        }
    }
}