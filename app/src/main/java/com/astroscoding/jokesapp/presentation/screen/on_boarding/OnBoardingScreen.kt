package com.astroscoding.jokesapp.presentation.screen.on_boarding

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.astroscoding.jokesapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
    onDone: () -> Unit
) {


    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pos ->
            PagerScreen(
                onBoardingItem = onBoardingItems[pos]
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color(0xfffeb739),
            inactiveColor = Color(0xFF86652A),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 16.dp)
        )

        if (pagerState.currentPage == onBoardingItems.lastIndex) {
            ArrowIconButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                ShowAnimated = pagerState.targetPage == onBoardingItems.lastIndex,
                onClicked = {
                    onBoardingViewModel.setCompleted()
                    onDone()
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ArrowIconButton(
    modifier: Modifier,
    onClicked: () -> Unit,
    ShowAnimated: Boolean
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val xOffset by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 90.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    LaunchedEffect(key1 = ShowAnimated) {
        if (ShowAnimated) delay(500) else delay(0)
        startAnimation = !startAnimation
    }

    IconButton(
        onClick = onClicked,
        modifier = modifier
            .offset(x = xOffset)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xfffeb739))
    ) {
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}


@Composable
fun PagerScreen(
    modifier: Modifier = Modifier,
    onBoardingItem: OnBoardingItem
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF281d29))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.TopCenter)
        ) {

            Box(
                modifier = Modifier
                    .size(500.dp)
                    .offset(150.dp, (-75).dp)
                    .alpha(.4f)
                    .clip(CircleShape)
                    .background(Color(0xFFA7614F))
                    .align(Alignment.TopEnd)
            )

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .offset((-50).dp, (-30).dp)
                    .alpha(0.55f)
                    .clip(CircleShape)
                    .background(Color(0xfffeb739))
                    .align(Alignment.BottomStart)
            )

            Image(
                painter = painterResource(id = onBoardingItem.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(Color(0xFF423144))
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = onBoardingItem.title,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = onBoardingItem.description,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
        }
    }
}

data class OnBoardingItem(
    val title: AnnotatedString,
    val description: AnnotatedString,
    @DrawableRes val image: Int
)

/*@Preview
@Composable
fun PreviewPager1() {
    PagerScreen(onBoardingItem = onBoardingItems.first())
}

@Preview
@Composable
fun PreviewPager2() {
    PagerScreen(onBoardingItem = onBoardingItems[1])
}

@Preview
@Composable
fun PreviewPager3() {
    PagerScreen(onBoardingItem = onBoardingItems.last())
}*/

@Preview
@Composable
fun PreviewWelcomeScreen() {
    WelcomeScreen {

    }
}

val firstTitle = buildAnnotatedString {
    pushStyle(SpanStyle(color = Color.White))
    append("Laughter is ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("beneficial")
    pushStyle(SpanStyle(color = Color.White))
    append(" to your ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("health")
}

val firstDescription = buildAnnotatedString {
    pushStyle(SpanStyle(color = Color.White))
    append("Laughter is good for the ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("heart")
    pushStyle(SpanStyle(color = Color.White))
    append(". Laughter improves blood vessel function and boosts ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("blood flow")
    pushStyle(SpanStyle(color = Color.White))
    append(", which can help you avoid a ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("heart attack")
    pushStyle(SpanStyle(color = Color.White))
    append(" or other cardiovascular disorders.")
}

val secondTitle = buildAnnotatedString {
    pushStyle(SpanStyle(color = Color.White))
    append("Laughter may possibly ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("prolong")
    pushStyle(SpanStyle(color = Color.White))
    append(" your life.")
}

val secondDescription = buildAnnotatedString {
    pushStyle(SpanStyle(color = Color.White))
    append("People with a good sense of humor ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("outlived")
    pushStyle(SpanStyle(color = Color.White))
    append(" others who don't laugh nearly as much, according to a Norwegian study. For ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("cancer patients")
    pushStyle(SpanStyle(color = Color.White))
    append(", the difference was very noticeable.")
}

val thirdTitle = buildAnnotatedString {
    pushStyle(SpanStyle(color = Color.White))
    append("Laughter is a ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("calorie-burning")
    pushStyle(SpanStyle(color = Color.White))
    append(" activity.")
}

val thirdDescription = buildAnnotatedString {
    pushStyle(SpanStyle(color = Color.White))
    append("Okay, so it's hardly a replacement for working out at the ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("gym")
    pushStyle(SpanStyle(color = Color.White))
    append(", but one study found that laughing for 10 to 15 minutes a day can ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("burn around 40 calories")
    pushStyle(SpanStyle(color = Color.White))
    append(" which are enough to lose ")
    pushStyle(SpanStyle(color = Color(0xfffeb739)))
    append("3-4 pounds")
    pushStyle(SpanStyle(color = Color.White))
    append(" in a year.")
}

val onBoardingItems = listOf(
    OnBoardingItem(
        firstTitle,
        firstDescription,
        R.drawable.first_on_boarding
    ),
    OnBoardingItem(
        secondTitle,
        secondDescription,
        R.drawable.second_on_boarding
    ),
    OnBoardingItem(
        thirdTitle,
        thirdDescription,
        R.drawable.third_on_boarding
    )
)
