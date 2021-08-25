package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.summery.Summery

/**
 * Finishes the game and returns the summary of the match
 */
interface FinishSessionUseCase {
    fun finish(): Summery
}