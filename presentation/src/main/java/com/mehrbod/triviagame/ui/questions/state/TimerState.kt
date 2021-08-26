package com.mehrbod.triviagame.ui.questions.state

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

sealed class TimerState {
    object Empty : TimerState()
    @ExperimentalTime
    class UpdateTimeLeft(val time: Duration, val totalTime: Duration) : TimerState()
}