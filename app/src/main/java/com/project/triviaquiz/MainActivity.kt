package com.project.triviaquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.triviaquiz.presentation.start_quiz.StartQuizRoot
import com.project.triviaquiz.presentation.start_quiz.StartQuizViewModel
import com.project.triviaquiz.ui.theme.TriviaQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriviaQuizTheme {
                App()
            }
        }
    }
}

@Composable
private fun App() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets()
    ) { innerPadding ->
        val viewModel = viewModel { StartQuizViewModel() }
        StartQuizRoot(
            modifier = Modifier.padding(paddingValues = innerPadding),
            viewModel = viewModel
        )
    }
}