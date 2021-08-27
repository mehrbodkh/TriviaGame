package com.mehrbod.triviagame.ui.summery.state

import com.mehrbod.domain.model.summery.Summery

sealed class SummeryUIState {
    object Empty : SummeryUIState()
    class ShowSummery(val summery: Summery) : SummeryUIState()
}