package com.mehrbod.domain.interactor

/**
 * Provide the ability to check whether the user can use extra time ability or not.
 * By using the method, it suppose that user used it.
 */
interface AddExtraTimeUseCase {
    fun addExtraTime(): Boolean
}