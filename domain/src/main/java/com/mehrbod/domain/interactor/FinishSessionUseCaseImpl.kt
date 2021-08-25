package com.mehrbod.domain.interactor

import com.mehrbod.domain.repository.GameSessionRepository
import javax.inject.Inject

class FinishSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : FinishSessionUseCase {

    override fun finishSession() {
        gameSessionRepository.clear()
    }
}