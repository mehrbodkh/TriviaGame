package com.mehrbod.triviagame.ui.summery

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mehrbod.domain.interactor.FinishSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SummeryViewModel @Inject constructor(
    private val finishSessionUseCase: FinishSessionUseCase
): ViewModel() {

    init {
        val summery = finishSessionUseCase.finish()
        Log.d("Mehrbod", summery.toString())
    }
}