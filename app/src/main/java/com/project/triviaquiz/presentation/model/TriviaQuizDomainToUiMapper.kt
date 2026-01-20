package com.project.triviaquiz.presentation.model

import com.project.triviaquiz.domain.model.TriviaQuizDomain

fun TriviaQuizDomain.toUi(): TriviaQuizUi {
    val shuffledAnswers = buildList {
        add(correctAnswer ?: "")
        addAll(incorrectAnswers ?: emptyList())
    }.shuffled()
    return TriviaQuizUi(
        id = id ?: "",
        question = question?.text ?: "",
        correctAnswer = correctAnswer ?: "",
        answerOptions = shuffledAnswers
    )
}