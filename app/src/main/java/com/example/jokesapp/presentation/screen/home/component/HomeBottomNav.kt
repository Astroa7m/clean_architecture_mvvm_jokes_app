package com.example.jokesapp.presentation.screen.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.jokesapp.presentation.core.util.Screens

@Composable
fun HomeBottomNav(
    onBottomItemClicked: (Screens) -> Unit,
    backStackEntry: State<NavBackStackEntry?>?
) {
    BottomNavigation(
        modifier = Modifier
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
            .height(100.dp),
        //backgroundColor = Color.White,
        elevation = 0.dp,
        backgroundColor = Color(0xFF2c2c2c)
    ) {
        val bottomMenuItems = listOf(
            BottomMenuItems(
                Icons.Outlined.Home,
                Icons.Default.Home,
                "Home"
            ),
            BottomMenuItems(
                Icons.Outlined.List,
                Icons.Default.List,
                "Statistics"
            )
        )

        bottomMenuItems.forEachIndexed { index, menuItem ->
            val isSelected = backStackEntry?.value?.destination?.route == menuItem.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) menuItem.iconSelected else menuItem.iconUnSelected,
                        contentDescription = menuItem.desc,
                        modifier = Modifier.size(28.dp)
                    )
                },
                selected = isSelected,

                selectedContentColor = Color(0xFF8d5185),
                unselectedContentColor = Color(0xFF8d5185).copy(0.5f),
                onClick = {
                    onBottomItemClicked(if(index==0)Screens.HomeScreen else Screens.Statistics)
                }
            )
            if (index != bottomMenuItems.size - 1) {
                Spacer(modifier = Modifier.width(120.dp))
            }
        }
    }
}

data class BottomMenuItems(
    val iconUnSelected: ImageVector,
    val iconSelected: ImageVector,
    val desc: String,
    val route: String = desc.lowercase()
)
