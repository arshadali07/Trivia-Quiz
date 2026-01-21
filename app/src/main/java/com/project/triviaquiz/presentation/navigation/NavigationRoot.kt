package com.project.triviaquiz.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.project.triviaquiz.presentation.quiz_section.QuizSectionRoot
import com.project.triviaquiz.presentation.score_section.ScoreSectionRoot
import com.project.triviaquiz.presentation.start_quiz.StartQuizRoot
import com.project.triviaquiz.presentation.start_quiz.StartQuizViewModel
import com.project.triviaquiz.presentation.util.MAX_TIME_COUNT

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
                var lifecycle by remember {
                    mutableStateOf(Lifecycle.Event.ON_CREATE)
                }
                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        lifecycle = event
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)

                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        viewModel.pauseTimer()
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        viewModel.resumeTimer()
                    }
                    else -> Unit
                }
                val timer by viewModel.timerCount.collectAsStateWithLifecycle(MAX_TIME_COUNT)
                QuizSectionRoot(
                    viewModel = viewModel,
                    timer = { timer },
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