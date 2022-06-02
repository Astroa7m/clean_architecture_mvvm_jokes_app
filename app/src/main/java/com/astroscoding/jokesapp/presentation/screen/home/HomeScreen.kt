package com.astroscoding.jokesapp.presentation.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.astroscoding.jokesapp.presentation.core.component.DefaultAppBar
import com.astroscoding.jokesapp.presentation.core.util.Screens
import com.astroscoding.jokesapp.presentation.screen.home.component.*

@Composable
fun HomeScreen(
    backStackEntry: State<NavBackStackEntry?>?,
    feelingLuckyDialogViewModel: FeelingLuckyDialogViewModel = hiltViewModel(),
    onHomeScreenComponentClicked: (Screens) -> Unit
) {

    var shouldShowLuckyJokeDialog by remember {
        mutableStateOf(false)
    }
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    Scaffold(
        topBar = {
            DefaultAppBar(
                "Home",
                isNotHome = false,
                onFeelingLuckyIconClicked = { shouldShowLuckyJokeDialog = true }) {
                onHomeScreenComponentClicked(Screens.FavouriteJokes)
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                cutoutShape = CircleShape,
                elevation = 22.dp,
                backgroundColor = Color(0xFF2c2c2c)
            ) {
                HomeBottomNav(
                    onBottomItemClicked = onHomeScreenComponentClicked,
                    backStackEntry = backStackEntry
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color(0xFF8d5185),
                modifier = Modifier.shadow(
                    12.dp,
                    shape = MaterialTheme.shapes.medium.copy(CornerSize(percent = 50))
                ),
                onClick = { onHomeScreenComponentClicked(Screens.AddJoke) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Joke",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1e1c1e))
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp, 16.dp, 16.dp, it.calculateBottomPadding() + 16.dp)
            ) {
                //1st box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .size((screenWidth / 3).dp, (screenWidth / 2.5).dp)
                        .background(Color.Black)
                        .clickable { onHomeScreenComponentClicked(Screens.AllJokeScreen) }
                ) {

                    ImageHorizontalGradient(
                        modifier = Modifier.fillMaxSize(),
                        isReversed = true,
                        height = (screenWidth / 2.5).toFloat(),
                        colorStart = Color(0xFFe68c75),
                        colorEnd = Color(0xFFd35898)
                    )

                    CategoryItemComp(
                        categoryItem = Screens.AllJokeScreen,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    //2nd & 3rd boxes
                    Column(modifier = Modifier.weight(1f)) {
                        //2nd box
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .size((screenWidth / 2).dp)
                                .background(Color.DarkGray)
                                .clickable { onHomeScreenComponentClicked(Screens.ProgrammingJokeScreen) }
                        ) {

                            ImageVerticalGradient(
                                modifier = Modifier.fillMaxSize(),
                                height = (screenWidth / 2).toFloat(),
                                colorStart = Color(0xFFe56c95),
                                colorEnd = Color(0xFFa840b0)
                            )

                            CategoryItemComp(
                                categoryItem = Screens.ProgrammingJokeScreen,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        //3rd box
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .size((screenWidth / 2).dp, (screenWidth / 1.5).dp)
                                .background(Color.DarkGray)
                                .clickable { onHomeScreenComponentClicked(Screens.MiscJokeScreen) }
                        ) {

                            ImageHorizontalGradient(
                                modifier = Modifier.fillMaxSize(),
                                height = (screenWidth / 1.5).toFloat(),
                                colorStart = Color(0xFFcd4990),
                                colorEnd = Color(0xFFa746b7)
                            )

                            CategoryItemComp(
                                categoryItem = Screens.MiscJokeScreen,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //6h box

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .size((screenWidth / 2).dp)
                                .background(Color.DarkGray)
                                .clickable { onHomeScreenComponentClicked(Screens.SpookyJokeScreen) }
                        ) {

                            ImageVerticalGradient(
                                modifier = Modifier.fillMaxSize(),
                                isReversed = true,
                                height = (screenWidth / 2).toFloat(),
                                colorEnd = Color(0xFFcd4990),
                                colorStart = Color(0xFFa746b7)
                            )

                            CategoryItemComp(
                                categoryItem = Screens.SpookyJokeScreen,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    // 4th and 5th boxes
                    Column(modifier = Modifier.weight(1f)) {
                        //4th box
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .size((screenWidth / 2).dp, (screenWidth / 1.5).dp)
                                .background(Color.DarkGray)
                                .clickable { onHomeScreenComponentClicked(Screens.DarkJokeScreen) }

                        ) {

                            ImageHorizontalGradient(
                                modifier = Modifier.fillMaxSize(),
                                isReversed = true,
                                height = (screenWidth / 1.5).toFloat(),
                                colorStart = Color(0xFFd362b4),
                                colorEnd = Color(0xFFe16aa3)
                            )

                            CategoryItemComp(
                                categoryItem = Screens.DarkJokeScreen,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        //5th box
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .size((screenWidth / 2).dp)
                                .background(Color.DarkGray)
                                .clickable { onHomeScreenComponentClicked(Screens.PunJokeScreen) }
                        ) {

                            ImageHorizontalGradient(
                                modifier = Modifier.fillMaxSize(),
                                isReversed = true,
                                height = (screenWidth / 2).toFloat(),
                                colorStart = Color(0xFFc550ab),
                                colorEnd = Color(0xFFd35fba)
                            )

                            CategoryItemComp(
                                categoryItem = Screens.PunJokeScreen,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //7th box

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .size((screenWidth / 2).dp)
                                .background(Color.DarkGray)
                                .clickable { onHomeScreenComponentClicked(Screens.Christmas) }
                        ) {

                            ImageHorizontalGradient(
                                modifier = Modifier.fillMaxSize(),
                                height = (screenWidth / 2).toFloat(),
                                colorStart = Color(0xFFb651c9),
                                colorEnd = Color(0xFF9e68d8)
                            )

                            CategoryItemComp(
                                categoryItem = Screens.Christmas,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            if (shouldShowLuckyJokeDialog) {
                FeelingLuckyJokeDialog(
                    onDismiss = {
                        shouldShowLuckyJokeDialog = false
                        feelingLuckyDialogViewModel.getAllJokes(count = 2)
                    }, modifier = Modifier.align(
                        Alignment.Center
                    ),
                    viewModel = feelingLuckyDialogViewModel
                )
            }

        }
    }
}


@Composable
fun FeelingLuckyJokeDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    viewModel: FeelingLuckyDialogViewModel
) {

    val state = viewModel.state.value
    val joke = remember(state.jokes, state.jokes.size, state) {
        state.jokes.random()
    }

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = modifier
                .background(Color.Black.copy(0.25f))
                .wrapContentSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Feeling Lucky Joke",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier.paddingFromBaseline(bottom = 16.dp)
            )

            if (state.errorMessage.isNotBlank())
                Text(
                    text = state.errorMessage,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colors.error
                )
            if (state.jokes.isNotEmpty()) {
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
                        viewModel.onFavouriteItemClicked(joke)
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


}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Home_Dark")
@Composable
fun PreviewHomeScreenDark() {
    HomeScreen(null) {

    }
}