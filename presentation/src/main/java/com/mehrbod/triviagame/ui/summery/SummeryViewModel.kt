package com.mehrbod.triviagame.ui.summery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehrbod.domain.interactor.FinishSessionUseCase
import com.mehrbod.triviagame.ui.summery.state.SummeryUIEvent
import com.mehrbod.triviagame.ui.summery.state.SummeryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummeryViewModel @Inject constructor(
    private val finishSessionUseCase: FinishSessionUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<SummeryUIState>(SummeryUIState.Empty)
    val uiState: StateFlow<SummeryUIState> = _uiState

    private val _uiEvent = MutableSharedFlow<SummeryUIEvent>()
    val uiEvent: SharedFlow<SummeryUIEvent> = _uiEvent

    init {
        val summery = finishSessionUseCase.finish()
        _uiState.value = SummeryUIState.ShowSummery(summery)
    }

    fun onReplayClicked() {
        viewModelScope.launch {
            _uiEvent.emit(SummeryUIEvent.NavigateToStartScreen)
        }
    }
}