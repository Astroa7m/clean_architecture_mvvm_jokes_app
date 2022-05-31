package com.example.jokesapp.presentation.screen.add_joke.comp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jokesapp.presentation.screen.add_joke.AddJokeViewModel
import com.example.jokesapp.presentation.screen.home.component.categories
import kotlinx.coroutines.launch

val languageList = listOf(
    "en",
    "cs",
    "de",
    "es",
    "fr",
    "pt",
    "other"
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddJokeMainScreen(
    viewModel: AddJokeViewModel,
    single: Boolean,
    onCheckChanged: (Boolean) -> Unit
) {

    val bringIntoViewRequester1 = remember { BringIntoViewRequester() }
    val bringIntoViewRequester2 = remember { BringIntoViewRequester() }
    val bringIntoViewRequester3 = remember { BringIntoViewRequester() }
    val bringIntoViewRequester4 = remember { BringIntoViewRequester() }

    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    var shouldShowCustomLanguageTextField by remember {
        mutableStateOf(false)
    }

    viewModel.onCustomLanguageTextChanged(languageList[0])

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1e1c1e))
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DropDownAddJokeMenu {
            viewModel.setCategory(it)
        }
        Spacer(modifier = Modifier.height(8.dp))
        JokeTypeSwitch(isSingle = single, onCheckChanged = onCheckChanged)
        Spacer(modifier = Modifier.height(8.dp))

        //when single part
        AnimatedVisibility(visible = single) {
            OutlinedTextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.White.copy(alpha = ContentAlpha.medium),
                    textColor = Color.White
                ),
                value = viewModel.joke.value,
                onValueChange = viewModel::onJokeTextChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .bringIntoViewRequester(bringIntoViewRequester1)
                    .onFocusEvent { focusState ->
                        if (focusState.isFocused) {
                            scope.launch {
                                bringIntoViewRequester1.bringIntoView()
                            }
                        }
                    },
                label = { Text(text = "type the joke") }
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        //when double parts
        AnimatedVisibility(visible = single.not()) {
            Column {
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White.copy(alpha = ContentAlpha.medium),
                        textColor = Color.White
                    ),
                    value = viewModel.setupText.value,
                    onValueChange = viewModel::onSetupTextChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .bringIntoViewRequester(bringIntoViewRequester2)
                        .onFocusEvent { focusState ->
                            if (focusState.isFocused) {
                                scope.launch {
                                    bringIntoViewRequester2.bringIntoView()
                                }
                            }
                        },
                    label = { Text(text = "type the setup") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White.copy(alpha = ContentAlpha.medium),
                        textColor = Color.White
                    ),
                    value = viewModel.deliveryText.value,
                    onValueChange = viewModel::onDeliveryTextChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .bringIntoViewRequester(bringIntoViewRequester3)
                        .onFocusEvent { focusState ->
                            if (focusState.isFocused) {
                                scope.launch {
                                    bringIntoViewRequester3.bringIntoView()
                                }
                            }
                        },
                    label = { Text(text = "type the delivery") }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        DropDownAddJokeMenu(
            listOfItems = languageList
        ) {
            shouldShowCustomLanguageTextField = it == "other"
            if (!shouldShowCustomLanguageTextField) {
                viewModel.onCustomLanguageTextChanged(it)
            }
        }

        AnimatedVisibility(shouldShowCustomLanguageTextField) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.White.copy(alpha = ContentAlpha.medium),
                    textColor = Color.White
                ),
                value = viewModel.customLanguageText.value.take(2),
                onValueChange = viewModel::onCustomLanguageTextChanged,
                label = { Text(text = "type the language code") },
                modifier = Modifier
                    .bringIntoViewRequester(bringIntoViewRequester4)
                    .onFocusEvent { focusState ->
                        if (focusState.isFocused) {
                            scope.launch {
                                bringIntoViewRequester4.bringIntoView()
                            }
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.addJoke(single)
        }) {
            Text(text = "Add Joke")
        }
    }
}

@Composable
fun DropDownAddJokeMenu(
    modifier: Modifier = Modifier,
    listOfItems: List<String>? = null,
    onDropDownItemSelected: (String) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    val list = listOfItems ?: categories.mapNotNull { it.title.takeIf { title -> title != "All" } }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 1f,
        animationSpec = tween(
            durationMillis = 300
        )
    )


    var dropChoice by remember { mutableStateOf(list[0]) }

    Box(modifier = modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = dropChoice.uppercase(),
                color = Color.White
            )

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "drop down",
                tint = Color.White,
                modifier = Modifier
                    .rotate(rotation)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            list.forEach {
                DropdownMenuItem(onClick = {
                    dropChoice = it
                    expanded = false
                    onDropDownItemSelected(it)
                }) {
                    Text(text = it.uppercase())
                }
            }

        }
    }

}


@Composable
fun JokeTypeSwitch(isSingle: Boolean, onCheckChanged: (Boolean) -> Unit) {

    var checked by remember { mutableStateOf(isSingle) }

    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Single",
            color = Color.White
        )
        Switch(
            checked = !checked,
            onCheckedChange = {
                checked = !checked
                onCheckChanged(checked)
            }
        )
        Text(
            text = "Two parts",
            color = Color.White
        )
    }
}


@Preview
@Composable
fun PreviewJokeTypeSwitch() {
    JokeTypeSwitch(false) {

    }
}
