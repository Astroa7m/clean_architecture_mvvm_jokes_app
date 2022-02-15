package com.example.jokesapp.presentation.screen.favourite_jokes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.presentation.core.component.DefaultAppBar
import com.example.jokesapp.presentation.core.component.JokeListItem
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun FavouriteJokesScreen(viewModel: FavouriteJokesViewModel = hiltViewModel()) {
    val items = viewModel.state.value
    val lazyListState = rememberLazyListState()
    var shouldShowDialog by remember {
        mutableStateOf(false)
    }

    val config = LocalConfiguration.current

    Scaffold(
        topBar = {
            DefaultAppBar(
                ScreenTitle = "Favourite Jokes",
                isLocal = true,
                iconVector = Icons.Default.FavoriteBorder
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                state = lazyListState,
                flingBehavior = rememberSnapperFlingBehavior(
                    lazyListState,
                    snapOffsetForItem = SnapOffsets.Start,
                    maximumFlingDistance = { layoutInfo->
                        val scrollLength = layoutInfo.endScrollOffset - layoutInfo.startScrollOffset
                        scrollLength.toFloat()
                    }
                )
            ) {

                items(
                    items.jokes
                ) { item ->
                    JokeListItem(
                        joke = item, viewModel::onFavouriteItemClicked, modifier = Modifier.size(
                            config.screenWidthDp.dp
                        )
                    )
                }
            }

            if (items.jokes.isEmpty())
                Text(
                    text = "You don't have any favourite jokes",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    color = Color.White
                )

            AnimatedVisibility (shouldShowDialog) {
                Dialog(
                    onDismissRequest = { shouldShowDialog = false },
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black.copy(0.25f))
                            .padding(16.dp)
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Are you sure you want to delete the favourite jokes?",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Justify
                        )
                        Row(
                            modifier = Modifier
                        ) {
                            TextButton(onClick = { shouldShowDialog = false }) {
                                Text(text = "Cancel")
                            }
                            TextButton(onClick = {
                                viewModel.unFavouriteAllJokes()
                                shouldShowDialog = false
                            }) {
                                Text(text = "Yes")
                            }
                        }
                    }
                }
            }

            if (items.jokes.isNotEmpty()) {
                IconButton(
                    onClick = {
                        shouldShowDialog = true
                    },
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Un favourite all"
                    )
                }
            }
        }
    }
}