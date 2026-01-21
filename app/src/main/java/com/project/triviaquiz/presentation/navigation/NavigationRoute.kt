package com.project.triviaquiz.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationRoute : NavKey {

    @Serializable
    data object StartQuiz : NavigationRoute

    @Serializable
    data object QuizSection : NavigationRoute

    @Serializable
    data object ScoreSection : NavigationRoute
}