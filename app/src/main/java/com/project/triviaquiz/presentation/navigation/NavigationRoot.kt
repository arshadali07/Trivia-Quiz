package com.project.triviaquiz.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.project.triviaquiz.presentation.quiz_section.QuizSectionRoot
import com.project.triviaquiz.presentation.score_section.ScoreSectionRoot
import com.project.triviaquiz.presentation.start_quiz.StartQuizRoot
import com.project.triviaquiz.presentation.start_quiz.StartQuizViewModel

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(NavigationRoute.StartQuiz)
    val viewModel = viewModel { StartQuizViewModel() }
    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        entryProvider = entryProvider {
            entry<NavigationRoute.StartQuiz> {
                StartQuizRoot(
                    viewModel = viewModel,
                    onNavigateToQuizSection = {
                        backStack.add(NavigationRoute.QuizSection)
                    }
                )
            }
            entry<NavigationRoute.QuizSection> {
                QuizSectionRoot(
                    viewModel = viewModel,
                    navigateToScore = {
                        backStack.add(NavigationRoute.ScoreSection)
                        backStack.remove(NavigationRoute.QuizSection)
                    }
                )
            }
            entry<NavigationRoute.ScoreSection> {
                ScoreSectionRoot(
                    viewModel = viewModel,
                    onBackToHome = {
                        backStack.clear()
                        backStack.add(NavigationRoute.StartQuiz)
                    }
                )
            }
        }
    )
}