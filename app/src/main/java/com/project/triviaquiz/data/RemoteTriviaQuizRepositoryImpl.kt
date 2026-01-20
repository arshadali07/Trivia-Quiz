package com.project.triviaquiz.data

import com.project.triviaquiz.data.model.toDomain
import com.project.triviaquiz.domain.TriviaQuizRepository
import com.project.triviaquiz.domain.model.TriviaQuizDomain

class RemoteTriviaQuizRepositoryImpl(
    private val service: TriviaQuizService = RetrofitProvider.triviaQuizService()
) : TriviaQuizRepository {

    override suspend fun getTriviaQuestions(): Result<List<TriviaQuizDomain>> {
        val response = service.getTriviaQuestions()
        return if (response.isSuccessful) {
            val quizzes = response.body()
            if (!quizzes.isNullOrEmpty()) {
                val mappedQuizzes = quizzes.map { it.toDomain() }
                Result.success(mappedQuizzes)
            } else {
                Result.failure(Exception("No quizzes available at remote, please try again later"))
            }
        } else {
            Result.failure(Exception("Unable to fetch quizzes from some error might happen please try again after some time"))
        }
    }
}