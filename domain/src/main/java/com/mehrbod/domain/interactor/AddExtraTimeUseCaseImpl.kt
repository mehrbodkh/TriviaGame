package com.mehrbod.domain.interactor

import com.mehrbod.domain.repository.GameSessionRepository
import javax.inject.Inject

class AddExtraTimeUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : AddExtraTimeUseCase {

    override fun addExtraTime(): Boolean {
        return if (!gameSessionRepository.isUserUsedExtraTime()) {
            gameSessionRepository.setUserUsedExtraTime(true)
            true
        } else {
            false
        }
    }
}