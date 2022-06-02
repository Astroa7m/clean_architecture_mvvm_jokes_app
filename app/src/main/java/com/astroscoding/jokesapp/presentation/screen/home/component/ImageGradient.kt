package com.astroscoding.jokesapp.presentation.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun ImageHorizontalGradient(
    modifier: Modifier = Modifier,
    gradientAlpha: Float = 0.65f,
    isReversed: Boolean = false,
    colorStart: Color,
    colorEnd: Color,
    height: Float
) {

    val colors = listOf(colorStart, colorEnd)
    //val colors = listOf(Color(0xFFa1bafe), Color(0xFF8d5185))

    val gradient1 = Brush.horizontalGradient(
        colors = if(isReversed) colors.reversed() else colors,
        (height/2.5).toFloat()
    )

    Box(modifier = modifier
        .alpha(gradientAlpha)
        .background(gradient1))
}


@Composable
fun ImageVerticalGradient(
    modifier: Modifier = Modifier,
    gradientAlpha: Float = 0.65f,
    isReversed: Boolean = false,
    colorStart: Color,
    colorEnd: Color,
    height: Float
) {

    val colors = listOf(colorStart, colorEnd)
    //val colors = listOf(Color(0xFFa1bafe), Color(0xFF8d5185))

    val gradient1 = Brush.verticalGradient(
        colors = if(isReversed) colors.reversed() else colors,
        (height/2.5).toFloat()

    )

    Box(modifier = modifier
        .alpha(gradientAlpha)
        .background(gradient1))

}