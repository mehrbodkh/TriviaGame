package com.mehrbod.triviagame.ui.summery

import androidx.lifecycle.ViewModel
import com.mehrbod.domain.interactor.FinishSessionUseCase
import com.mehrbod.triviagame.ui.summery.state.SummeryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SummeryViewModel @Inject constructor(
    private val finishSessionUseCase: FinishSessionUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<SummeryUIState>(SummeryUIState.Empty)
    val uiState: StateFlow<SummeryUIState> = _uiState

    init {
        val summery = finishSessionUseCase.finish()
        _uiState.value = SummeryUIState.ShowSummery(summery)
    }
}