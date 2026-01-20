package com.project.triviaquiz.presentation.start_quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.project.triviaquiz.presentation.util.QuizBackground
import com.project.triviaquiz.ui.theme.TriviaQuizTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun StartQuizRoot(
    viewModel: StartQuizViewModel,
    onNavigateToQuizSection: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner, viewModel.uiEvent) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                viewModel.uiEvent.collect { event ->
                    when (event) {
                        is StartQuizEvent.ShowToastEvent -> {
                            snackBarHostState.showSnackbar(message = event.message)
                        }
                        is StartQuizEvent.NavigateToQuizSectionEvent -> onNavigateToQuizSection()
                    }
                }
            }
        }
    }

    StartQuizScreen(
        modifier = Modifier,
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
private fun StartQuizScreen(
    modifier: Modifier = Modifier,
    uiState: StartQuizUiState,
    onAction: (StartQuizAction) -> Unit
) {
    QuizBackground {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Welcome to Trivia Quiz Challenge",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (uiState.isApiLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            } else {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    onClick = { onAction(StartQuizAction.OnStartQuizAction) },
                    content = {
                        Text(
                            text = "Start quiz",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight(500)
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun StartQuizScreenPreview() {
    TriviaQuizTheme {
        StartQuizScreen(
            uiState = testUiState,
            onAction = {}
        )
    }
}

private val testUiState = StartQuizUiState()