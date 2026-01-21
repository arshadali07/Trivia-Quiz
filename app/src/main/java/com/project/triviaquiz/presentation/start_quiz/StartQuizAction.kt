package com.project.triviaquiz.presentation.start_quiz

import com.project.triviaquiz.presentation.model.TriviaQuizUi

sealed interface StartQuizAction {
    data object OnStartQuizAction : StartQuizAction
    data class OnAnswerClickAction(val quizUi: TriviaQuizUi) : StartQuizAction
    data object OnAnswerSubmitAction : StartQuizAction
}