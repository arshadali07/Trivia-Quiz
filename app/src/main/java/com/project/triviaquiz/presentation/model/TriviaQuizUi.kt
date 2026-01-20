package com.project.triviaquiz.presentation.model

data class TriviaQuizUi(
    val id: String,
    val question: String,
    val correctAnswer: String,
    val answerOptions: List<String>
)