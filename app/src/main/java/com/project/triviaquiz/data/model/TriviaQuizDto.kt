package com.project.triviaquiz.data.model

import com.google.gson.annotations.SerializedName

data class TriviaQuizDto(
    @SerializedName("category")
    val category: String?,
    @SerializedName("correctAnswer")
    val correctAnswer: String?,
    @SerializedName("difficulty")
    val difficulty: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("incorrectAnswers")
    val incorrectAnswers: List<String>?,
    @SerializedName("isNiche")
    val isNiche: Boolean?,
    @SerializedName("question")
    val question: QuestionDto?,
    @SerializedName("regions")
    val regions: List<Any>?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("type")
    val type: String?
)

data class QuestionDto(
    @SerializedName("text")
    val text: String?
)