package com.project.triviaquiz.data.model

import com.project.triviaquiz.domain.model.QuestionDomain
import com.project.triviaquiz.domain.model.TriviaQuizDomain

fun TriviaQuizDto.toDomain(): TriviaQuizDomain {
    return TriviaQuizDomain(
        id = id,
        category = category,
        question = question?.let { QuestionDomain(text = it.text) },
        correctAnswer = correctAnswer,
        incorrectAnswers = incorrectAnswers,
        difficulty = difficulty
    )
}