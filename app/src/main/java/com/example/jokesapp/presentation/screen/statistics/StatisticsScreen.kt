package com.example.jokesapp.presentation.screen.statistics

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.example.jokesapp.presentation.core.component.DefaultAppBar
import com.example.jokesapp.presentation.core.util.Screens
import com.example.jokesapp.presentation.screen.home.component.HomeBottomNav

@Composable
fun StatisticsScreen(
    backStackEntry: State<NavBackStackEntry?>?,
    viewModel: StatisticsViewModel = hiltViewModel(),
    onHomeScreenComponentClicked: (Screens) -> Unit
) {
    val statisticsText = viewModel.statisticsText.value
    Scaffold(
        topBar = {
            DefaultAppBar(
                ScreenTitle = "Statistics",
                isLocal = true,
                iconVector = Icons.Default.List
            )
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
                .padding(16.dp)
        ) {
            Text(
                text = if (statisticsText.isBlank()) "Statistics will be shown here once you start liking jokes" else "You Liked\n$statisticsText",
                modifier = Modifier.align(Alignment.Center),
                fontSize =20.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}