package com.astroscoding.jokesapp.presentation.screen.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.astroscoding.jokesapp.R
import com.astroscoding.jokesapp.presentation.core.Dimension.width
import com.astroscoding.jokesapp.presentation.core.util.Screens

@Composable
fun CategoryItemComp(
    categoryItem: Screens,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = categoryItem.icon),
            contentDescription = categoryItem.capitalizeFirst(),
            tint = Color.White,
            modifier = Modifier.size(10f.width().dp)
        )
        val text = buildAnnotatedString {
            pushStyle(
                SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
            append(categoryItem.capitalizeFirst())
            pop()
            pushStyle(
                SpanStyle(
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    color = Color.White.copy(alpha = 0.5f)
                )
            )
            append("\n\n${categoryItem.description}")
        }
        Text(
            text = text,
            fontSize = 4f.width().sp
        )
    }
}
