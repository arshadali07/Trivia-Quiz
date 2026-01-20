package com.project.triviaquiz.presentation.start_quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.triviaquiz.data.RemoteTriviaQuizRepositoryImpl
import com.project.triviaquiz.domain.TriviaQuizRepository
import com.project.triviaquiz.presentation.model.toUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StartQuizViewModel(
    private val repository: TriviaQuizRepository = RemoteTriviaQuizRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow(StartQuizUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<StartQuizEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: StartQuizAction) {
        when (action) {
            is StartQuizAction.OnStartQuizAction -> onStartQuiz()
        }
    }

    private fun onStartQuiz() = getTriviaQuestions()

    private fun getTriviaQuestions() {
        viewModelScope.launch {
            _uiState.update { it.copy(isApiLoading = true) }
            val result = repository.getTriviaQuestions()
            if (result.isSuccess) {
                _uiState.update { it.copy(isApiLoading = false) }
                val mappedQuizList = (result.getOrNull() ?: emptyList()).map { it.toUi() }
                _uiState.update { it.copy(quizList = mappedQuizList) }
            } else {
                val message = result.exceptionOrNull()?.message ?: "Something went wrong"
                _uiState.update { it.copy(isApiLoading = false) }
                _uiEvent.send(StartQuizEvent.ShowToastEvent(message))
            }
        }
    }
}