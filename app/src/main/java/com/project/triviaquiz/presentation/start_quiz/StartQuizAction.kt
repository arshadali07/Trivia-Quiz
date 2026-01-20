package com.project.triviaquiz.presentation.start_quiz

sealed interface StartQuizAction {
    data object OnStartQuizAction : StartQuizAction
}