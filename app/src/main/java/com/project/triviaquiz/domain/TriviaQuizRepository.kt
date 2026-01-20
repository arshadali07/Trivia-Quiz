package com.project.triviaquiz.domain

import com.project.triviaquiz.domain.model.TriviaQuizDomain

interface TriviaQuizRepository {
    suspend fun getTriviaQuestions(): Result<List<TriviaQuizDomain>>
}