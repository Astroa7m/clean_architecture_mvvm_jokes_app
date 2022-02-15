package com.example.jokesapp.presentation.screen.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
import com.example.jokesapp.R
import com.example.jokesapp.presentation.core.Dimension.width

@Composable
fun CategoryItemComp(
    categoryItem: CategoryItem,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = categoryItem.icon),
            contentDescription = categoryItem.title,
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
            append(categoryItem.title)
            pop()
            pushStyle(
                SpanStyle(
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    color = Color.White.copy(alpha = 0.5f)
                )
            )
            append("\n\n${categoryItem.desc}")
        }
        Text(
            text = text,
            fontSize = 4f.width().sp
        )
    }
}

val categories = listOf(
    CategoryItem(
        R.drawable.ic_misc,
        "All",
        "A mixture of laughter"
    ),
    CategoryItem(
        R.drawable.ic_code,
        "Programming",
        "Some bugs better left uncaught"
    ),
    CategoryItem(
        R.drawable.ic_all,
        "Misc",
        "Neatly mixed gags"
    ),
    CategoryItem(
        R.drawable.ic_dark,
        "Dark",
        "We know, it doesn't always shine"
    ),
    CategoryItem(
        R.drawable.ic_pun,
        "Pun",
        "How linguistic are you?"
    ),
    CategoryItem(
        R.drawable.ic_spooky,
        "Spooky",
        "One sweet day of scares!"
    ),
    CategoryItem(
        R.drawable.ic_christmas,
        "Christmas",
        "Ho Ho Ho!"
    )

)

data class CategoryItem(
    val icon: Int,
    val title: String,
    val desc: String
)

@Preview
@Composable
fun PreviewCategoryItemComp() {
    CategoryItemComp(
        categoryItem = CategoryItem(
            R.drawable.ic_all,
            "All",
            "A mixture of laughter"
        )
    )
}