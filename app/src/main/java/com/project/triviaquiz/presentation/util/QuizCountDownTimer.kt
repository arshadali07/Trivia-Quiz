package com.project.triviaquiz.presentation.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val MAX_TIME_COUNT = 60
class QuizCountDownTimer {

    val timerCount = MutableStateFlow(MAX_TIME_COUNT)

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isActive = false

    fun start() {
        if(isActive) return

        coroutineScope.launch {
            this@QuizCountDownTimer.isActive = true
            while(this@QuizCountDownTimer.isActive && timerCount.value > 0) {
                delay(1000L)
                timerCount.update { it.minus(1) }
            }
        }
    }

    fun pause() {
        isActive = false
    }

    fun resume() {
        start()
    }

    fun reset() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.Main)
        timerCount.update { MAX_TIME_COUNT }
        isActive = false
    }
}