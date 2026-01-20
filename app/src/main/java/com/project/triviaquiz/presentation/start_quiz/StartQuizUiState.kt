package com.project.triviaquiz.presentation.start_quiz

import com.project.triviaquiz.presentation.model.TriviaQuizUi

data class StartQuizUiState(
    val isApiLoading: Boolean = false,
    val quizList: List<TriviaQuizUi> = emptyList()
)