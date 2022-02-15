package com.example.jokesapp.presentation.core.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.jokesapp.R

@Composable
fun SearchAppBar(
    queryText: String,
    onQueryTextChange: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
    onCloseIconClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        color = Color(0xFF2c2c2c),
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        TextField(
            value = queryText,
            onValueChange = onQueryTextChange,
            modifier = Modifier.fillMaxSize(),
            placeholder = {
                Text(
                    text = "Search...",
                    Modifier.alpha(ContentAlpha.medium),
                    color = Color.White
                )
            },
            leadingIcon = {
                IconButton(onClick = onSearchIconClicked) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = onCloseIconClicked) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close icon",
                        tint = Color.White
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchIconClicked()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Composable
fun DefaultAppBar(
    ScreenTitle: String,
    onSearchIconClicked: () -> Unit = {},
    onDropDownMenuItemClicked: (List<DropDownMenuItemModel>) -> Unit = {},
    isNotHome: Boolean = true,
    isLocal: Boolean = false,
    icon: Int = 0,
    iconVector: ImageVector? = null,
    onFeelingLuckyIconClicked: () -> Unit = {},
    onFavouriteIconClicked: () -> Unit = {},
) {


    TopAppBar(
        modifier = Modifier.height(100.dp),
        backgroundColor = Color(0xFF2c2c2c),
        title = {
            if (!isNotHome) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = if (isLocal) Arrangement.Center else Arrangement.SpaceBetween
                ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onFeelingLuckyIconClicked()
                            },
                        painter = painterResource(id = R.drawable.ic_dice),
                        contentDescription = null,
                        tint = Color(0xFF8d5185),
                    )
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        Text(text = ScreenTitle, color = Color.White)
                    }
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { onFavouriteIconClicked() },
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = Color(0xFF8d5185)
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (!isLocal) {
                        IconButton(onClick = onSearchIconClicked) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }
                    }
                    Column(
                        modifier = if (isLocal) Modifier.fillMaxSize() else Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!isLocal) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                tint = Color.White,
                            )
                        } else {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = iconVector!!,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Text(text = ScreenTitle, color = Color.White)
                    }
                    if (!isLocal)
                        JokesDropDownMenu(onDropDownMenuItemClicked)
                }
            }
        }
    )
}