package com.astroscoding.jokesapp.presentation.core.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun JokesDropDownMenu(filters: Pair<Boolean, Boolean>, onItemChecked : (Pair<Boolean, Boolean>) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 1f,
        animationSpec = tween(
            durationMillis = 300
        )
    )

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
                CheckedBoxMenuItem(dropDownString = "One Part", checked = filters.first, onCheckedChange = {
                    onItemChecked(Pair(it, filters.second))
                })
                CheckedBoxMenuItem(dropDownString = "Two Parts", checked = filters.second, onCheckedChange = {
                    onItemChecked(Pair(filters.first, it))
                })
            }
        }
    }
}


@Composable
fun CheckedBoxMenuItem(
    dropDownString: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = dropDownString)
        Spacer(modifier = Modifier.width(8.dp))
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
    }
}
