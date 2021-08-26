package com.mehrbod.triviagame.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.ExperimentalTime


@ExperimentalTime
fun startTicker(length: Duration, period: Duration, initialDelay: Duration = Duration.ZERO) = flow {
    delay(initialDelay)
    var timeLeft = length
    while (timeLeft.isPositive()) {
        emit(timeLeft)
        delay(period)
        timeLeft = timeLeft.minus(Duration.milliseconds(1000L))
    }
}