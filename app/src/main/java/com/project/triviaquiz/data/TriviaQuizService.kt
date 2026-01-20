package com.project.triviaquiz.data

import com.project.triviaquiz.data.model.TriviaQuizDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaQuizService {

    @GET("v2/questions")
    suspend fun getTriviaQuestions(
        @Query("limit") limit: Int = 10
    ): Response<List<TriviaQuizDto>>
}