package com.example.jokesapp.presentation.screen.add_joke.comp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jokesapp.presentation.screen.add_joke.AddJokeViewModel
import com.example.jokesapp.presentation.screen.home.component.categories
import com.google.accompanist.flowlayout.FlowRow

val languageList = listOf(
    "en",
    "cs",
    "de",
    "es",
    "fr",
    "pt",
    "other"
)

@Composable
fun AddJokeMainScreen(
    viewModel: AddJokeViewModel,
    single: Boolean,
    onCheckChanged: (Boolean) -> Unit
) {

    var shouldShowCustomLanguageTextField by remember {
        mutableStateOf(false)
    }

    viewModel.onCustomLanguageTextChanged(languageList[0])

    var flags : List<CheckBoxItemModel> = checkBoxMenuList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1e1c1e))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DropDownAddJokeMenu{
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
                modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "type the delivery") }
                )
            }
        }

        CheckBoxGroup {
            flags = it
        }
        Spacer(modifier = Modifier.height(8.dp))
        DropDownAddJokeMenu(
            listOfItems = languageList
        ){
            shouldShowCustomLanguageTextField = it == "other"
            if(!shouldShowCustomLanguageTextField){
                viewModel.onCustomLanguageTextChanged(it)
            }
        }

        AnimatedVisibility(shouldShowCustomLanguageTextField){
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.White.copy(alpha = ContentAlpha.medium),
                    textColor = Color.White
                ),
                value = viewModel.customLanguageText.value.take(2),
                onValueChange = viewModel::onCustomLanguageTextChanged,
                label = { Text(text = "type the language code") }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.setFlags(flags)
            viewModel.addJoke(single)
        }) {
            Text(text = "Add Joke")
        }
    }
}

@Composable
fun CheckBoxGroup(
    modifier: Modifier = Modifier,
    onListChanged: (List<CheckBoxItemModel>) -> Unit
) {

    val list = remember {
        checkBoxMenuList
    }

    Column(modifier = modifier.padding(16.dp)) {
        FlowRow {
            list.forEachIndexed { index, item ->
                CheckBoxItem(title = item.title, isChecked = item.isChecked) { isChecked ->
                    list[index] = list[index].copy(isChecked = isChecked)
                    onListChanged(list)
                }
            }
        }
    }
}

@Composable
fun CheckBoxItem(
    title: String,
    isChecked: Boolean,
    onCheckedChang: (Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckedChang)
        Text(
            text = title,
            color = Color.White
        )
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

    val list = listOfItems ?: categories.mapNotNull {  it.title.takeIf { title -> title != "All" } }

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

var checkBoxMenuList = mutableStateListOf(
    CheckBoxItemModel(
        "Nsfw",
        false
    ),
    CheckBoxItemModel(
        "Religious",
        false
    ),
    CheckBoxItemModel(
        "Political",
        false
    ),
    CheckBoxItemModel(
        "Racist",
        false
    ),
    CheckBoxItemModel(
        "Sexist",
        false
    ),
    CheckBoxItemModel(
        "Explicit",
        false
    )
)

data class CheckBoxItemModel(
    val title: String,
    val isChecked: Boolean
)