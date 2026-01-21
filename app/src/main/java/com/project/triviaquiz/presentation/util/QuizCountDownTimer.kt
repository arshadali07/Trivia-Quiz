package com.project.triviaquiz.presentation.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val MAX_TIME_COUNT = 10
class QuizCountDownTimer {

    var timeCount by mutableIntStateOf(MAX_TIME_COUNT)

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isActive = false

    fun start() {
        if(isActive) return

        coroutineScope.launch {
            this@QuizCountDownTimer.isActive = true
            while(this@QuizCountDownTimer.isActive && timeCount > 0) {
                delay(1000L)
                timeCount -= 1
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
        timeCount = MAX_TIME_COUNT
        isActive = false
    }
}