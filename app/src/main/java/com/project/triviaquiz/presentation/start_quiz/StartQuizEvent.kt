package com.project.triviaquiz.presentation.start_quiz

sealed interface StartQuizEvent {
    data class ShowToastEvent(val message: String) : StartQuizEvent
    data object NavigateToQuizSectionEvent : StartQuizEvent
}