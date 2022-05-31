package com.example.jokesapp.presentation.core.component

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun JokesDropDownMenu(onItemChecked : (List<DropDownMenuItemModel>) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 1f,
        animationSpec = tween(
            durationMillis = 300
        )
    )

    val filterList = remember {
        mutableStateListOf(
            DropDownMenuItemModel("One Part", false),
            DropDownMenuItemModel("Two Parts", false)
        )
    }

    Box{
        IconButton(
            onClick = { expanded = !expanded }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop down",
                tint = Color.White,
                modifier = Modifier
                    .clickable { expanded = !expanded }
                    .rotate(rotation)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                filterList.forEachIndexed { index, item ->
                    CheckedBoxMenuItem(
                        dropDownMenuItemModel = item,
                        onCheckedChange = {
                            filterList[index] = item.copy(isChecked = it)
                            onItemChecked(filterList)
                        }
                    )
                    if(index != filterList.size-1)
                        Divider(modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

data class DropDownMenuItemModel(
    val text: String = "",
    val isChecked: Boolean = false
)


@Composable
fun CheckedBoxMenuItem(
    dropDownMenuItemModel: DropDownMenuItemModel,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = dropDownMenuItemModel.text)
        Spacer(modifier = Modifier.width(8.dp))
        Checkbox(checked = dropDownMenuItemModel.isChecked, onCheckedChange = onCheckedChange)
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDropDownMenu() {
    /*JokesDropDownMenu(selectedIndex = 0) {

    }*/
}