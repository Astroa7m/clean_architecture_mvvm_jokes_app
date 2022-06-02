package com.astroscoding.jokesapp.presentation.core.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.astroscoding.jokesapp.presentation.core.util.Screens
import com.astroscoding.jokesapp.presentation.screen.add_joke.AddJokeScreen
import com.astroscoding.jokesapp.presentation.screen.all_jokes.AllJokesScreen
import com.astroscoding.jokesapp.presentation.screen.christmas_jokes.ChristmasJokesScreen
import com.astroscoding.jokesapp.presentation.screen.dark_jokes.DarkJokesScreen
import com.astroscoding.jokesapp.presentation.screen.favourite_jokes.FavouriteJokesScreen
import com.astroscoding.jokesapp.presentation.screen.home.HomeScreen
import com.astroscoding.jokesapp.presentation.screen.misc_jokes.MiscJokeScreen
import com.astroscoding.jokesapp.presentation.screen.programming_jokes.ProgrammingJokeScreen
import com.astroscoding.jokesapp.presentation.screen.pun_jokes.PunJokesScreen
import com.astroscoding.jokesapp.presentation.screen.spooky_jokes.SpookyJokesScreen
import com.astroscoding.jokesapp.presentation.screen.statistics.StatisticsScreen

@Composable
fun NavController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route){
        composable(Screens.HomeScreen.route){
            HomeScreen(navController.currentBackStackEntryAsState()){ category->
                navController.navigate(category.route){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
        composable(Screens.AllJokeScreen.route){
            AllJokesScreen()
        }
        composable(Screens.ProgrammingJokeScreen.route){
            ProgrammingJokeScreen()
        }
        composable(Screens.DarkJokeScreen.route){
            DarkJokesScreen()
        }
        composable(Screens.DarkJokeScreen.route){
            DarkJokesScreen()
        }
        composable(Screens.MiscJokeScreen.route){
            MiscJokeScreen()
        }
        composable(Screens.PunJokeScreen.route){
            PunJokesScreen()
        }
        composable(Screens.SpookyJokeScreen.route){
            SpookyJokesScreen()
        }
        composable(Screens.Christmas.route){
            ChristmasJokesScreen()
        }
        composable(Screens.FavouriteJokes.route){
            FavouriteJokesScreen()
        }
        composable(Screens.Statistics.route){
            StatisticsScreen(navController.currentBackStackEntryAsState()){
                    category->
                navController.navigate(category.route){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
        composable(Screens.AddJoke.route){
            AddJokeScreen()
        }

    }
}