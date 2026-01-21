package com.project.triviaquiz.presentation.score_section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.triviaquiz.R
import com.project.triviaquiz.presentation.start_quiz.StartQuizUiState
import com.project.triviaquiz.presentation.start_quiz.StartQuizViewModel
import com.project.triviaquiz.presentation.util.QuizBackground
import com.project.triviaquiz.ui.theme.Purple40
import com.project.triviaquiz.ui.theme.TriviaQuizTheme

@Composable
fun ScoreSectionRoot(
    viewModel: StartQuizViewModel,
    onBackToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ScoreSectionScreen(
        modifier = Modifier,
        uiState = uiState,
        onBackToHome = onBackToHome
    )
}

@Composable
private fun ScoreSectionScreen(
    modifier: Modifier = Modifier,
    uiState: StartQuizUiState,
    onBackToHome: () -> Unit
) {
    val quizList = uiState.quizList
    val answeredSize = quizList.filter { it.userAnswer == it.correctAnswer }
    val answerText = "${answeredSize.size} / ${quizList.size}"
    QuizBackground {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_trophy),
                contentDescription = "Trophy Image",
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .aspectRatio(1f)
            )
            Text(
                text = "Congratulations",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Your score",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = answerText,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "You did a great job, learn more by taking\nanother quiz",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onBackToHome,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple40
                ),
                shape = RoundedCornerShape(12.dp),
                content = {
                    Text(
                        text = "Back to home",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(500),
                    )
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScoreSectionScreenPreview() {
    TriviaQuizTheme {
        ScoreSectionScreen(
            uiState = testUiState,
            onBackToHome = {}
        )
    }
}

private val testUiState = StartQuizUiState()