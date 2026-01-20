package com.project.triviaquiz.domain.model

data class TriviaQuizDomain(
    val id: String?,
    val category: String?,
    val question: QuestionDomain?,
    val correctAnswer: String?,
    val incorrectAnswers: List<String>?,
    val difficulty: String?,
)

data class QuestionDomain(
    val text: String?
)