package com.project.triviaquiz.presentation.util

import androidx.compose.ui.graphics.Brush
import com.project.triviaquiz.ui.theme.Pink80
import com.project.triviaquiz.ui.theme.Purple40
import com.project.triviaquiz.ui.theme.Purple80

object BrushUtil {
    val gradientBackgroundBrush = Brush.verticalGradient(
        colors = listOf(Pink80, Purple80, Purple40)
    )
}