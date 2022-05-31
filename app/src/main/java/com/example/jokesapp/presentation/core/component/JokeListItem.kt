package com.example.jokesapp.presentation.core.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jokesapp.data.remote.dto.Flags
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.presentation.ui.theme.JokesAppTheme

@Composable
fun JokeListItem(
    joke: Joke,
    onFavouriteItemClicked: (Joke) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            val favouriteIcon =
                if (joke.isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

            if (joke.type == "twopart") {
                val builder = buildAnnotatedString {
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append("Setup: ")
                    pop()
                    append("${joke.setup.toString()}\n")
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append("Delivery: ")
                    pop()
                    append(joke.delivery.toString())
                }
                Text(
                    text = builder,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify
                )

            } else {
                val builder = buildAnnotatedString {
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append("Joke: ")
                    pop()
                    append(joke.joke.toString())
                }
                Text(
                    text = builder,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
            IconButton(
                onClick = {
                    onFavouriteItemClicked(joke)
                },
            ) {
                Icon(
                    imageVector = favouriteIcon,
                    contentDescription = "Favorite",
                    tint = Color.Red.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJokeOnePartListItem() {
    JokesAppTheme {
        val joke =
            "Today I learned that changing random stuff until your program works is \"hacky\" and a \"bad coding practice\" but if you do it fast enough it's \"Machine Learning\" and pays 4x your current salary."
        val jokeModel = Joke(
            0,
            "Programming",
            joke,
            flags = Flags(true, true, true, true, true, true),
            lang = "en",
            safe = true,
            type = "onepart",
            isFavourite = false
        )
        JokeListItem(joke = jokeModel, onFavouriteItemClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJokeTwoPartsListItem() {
    JokesAppTheme {
        val setup = "What's the difference between an in-law and an outlaw?"
        val delivery = "An outlaw is wanted."
        val jokeModel = Joke(
            0,
            "Dark",
            setup = setup,
            delivery = delivery,
            flags = Flags(true, true, true, true, true, true),
            lang = "en",
            safe = true,
            type = "twopart",
            isFavourite = true
        )
        JokeListItem(joke = jokeModel, onFavouriteItemClicked = {})
    }
}