package com.mehrbod.domain.interactor

import com.mehrbod.domain.model.summery.Summery

interface GetSummeryUseCase {
    fun getSummery(): Summery
}