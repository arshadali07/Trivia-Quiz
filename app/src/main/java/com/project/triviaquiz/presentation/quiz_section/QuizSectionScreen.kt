package com.project.triviaquiz.presentation.quiz_section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.triviaquiz.presentation.model.TriviaQuizUi
import com.project.triviaquiz.presentation.start_quiz.StartQuizAction
import com.project.triviaquiz.presentation.start_quiz.StartQuizUiState
import com.project.triviaquiz.presentation.start_quiz.StartQuizViewModel
import com.project.triviaquiz.presentation.util.QuizBackground
import com.project.triviaquiz.presentation.util.QuizSectionTestData
import com.project.triviaquiz.presentation.util.RoundedCornerCheckbox
import com.project.triviaquiz.ui.theme.Purple40
import com.project.triviaquiz.ui.theme.Purple80
import com.project.triviaquiz.ui.theme.TriviaQuizTheme
import kotlinx.coroutines.launch

@Composable
fun QuizSectionRoot(
    modifier: Modifier = Modifier,
    viewModel: StartQuizViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedQuiz by viewModel.selectedQuiz.collectAsStateWithLifecycle()

    QuizSectionScreen(
        modifier = modifier,
        uiState = uiState,
        selectedQuiz = selectedQuiz,
        onAction = viewModel::onAction
    )
}

@Composable
private fun QuizSectionScreen(
    modifier: Modifier = Modifier,
    uiState: StartQuizUiState,
    selectedQuiz: TriviaQuizUi?,
    onAction: (StartQuizAction) -> Unit
) {
    val quizList = uiState.quizList
    val pagerState = rememberPagerState(pageCount = { quizList.size })
    val scope = rememberCoroutineScope()
    QuizBackground {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
        ) {
            QuizSectionTopBar(
                attemptedQuestion = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
                onBackClick = {}
            )
            QuizSectionProgressBar(
                modifier = Modifier,
                progress = { (pagerState.currentPage + 1).toFloat() / pagerState.pageCount }
            )
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                contentPadding = PaddingValues(horizontal = 24.dp),
                pageSpacing = 24.dp
            ) { index ->
                quizList.getOrNull(index)?.let { quiz ->
                    QuizSectionCard(
                        modifier = Modifier,
                        quizUi = quiz,
                        selectedQuiz = selectedQuiz,
                        onAnswerClick = { onAction(StartQuizAction.OnAnswerClickAction(it)) },
                        onNextClick = {
//                            onAction(StartQuizAction.OnNextClickAction)
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun QuizSectionTopBar(
    modifier: Modifier = Modifier,
    attemptedQuestion: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = onBackClick,
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back Arrow",
                )
            }
        )
        Text(
            text = attemptedQuestion,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight(500),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun QuizSectionProgressBar(
    modifier: Modifier = Modifier,
    progress: () -> Float
) {
    LinearProgressIndicator(
        drawStopIndicator = {},
        gapSize = (-10).dp,
        progress = { progress() },
        trackColor = Purple80.copy(alpha = 0.8f),
        color = Purple40,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(20.dp),
    )
}

@Composable
private fun QuizSectionCard(
    modifier: Modifier = Modifier,
    selectedQuiz: TriviaQuizUi?,
    quizUi: TriviaQuizUi,
    onAnswerClick: (TriviaQuizUi) -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(24.dp)
    ) {
        Text(
            text = quizUi.question,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight(800),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        quizUi.answerOptions.fastForEach { answer ->
            QuizAnswerItemComponent(
                answer = answer,
                isSelected = answer == selectedQuiz?.userAnswer,
                onAnswerClick = { onAnswerClick(quizUi.copy(userAnswer = answer)) },
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(45.dp))
        Button(
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple40
            ),
            shape = RoundedCornerShape(12.dp),
            content = {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}

@Composable
private fun QuizAnswerItemComponent(
    modifier: Modifier = Modifier,
    answer: String,
    isSelected: Boolean,
    onAnswerClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    val borderColor = if (isSelected) Color(0xFFE2CFFC) else Color(0xFFE2E5DB)
    val backgroundColor = if (isSelected) Color(0xFFE2CFFC).copy(alpha = 0.5f) else Color.White
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .clickable(onClick = onAnswerClick)
            .padding(16.dp)
    ) {
        RoundedCornerCheckbox(
            isChecked = isSelected,
            onValueChange = {},
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = answer,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight(500),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun QuizSectionScreenPreview() {
    TriviaQuizTheme {
        QuizSectionScreen(
            uiState = testUiState,
            selectedQuiz = null,
            onAction = {}
        )
    }
}

private val testUiState = StartQuizUiState(
    quizList = QuizSectionTestData.getTestData()
)