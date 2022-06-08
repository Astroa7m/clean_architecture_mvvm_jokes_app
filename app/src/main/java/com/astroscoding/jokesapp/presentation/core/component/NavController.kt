package com.astroscoding.jokesapp.presentation.core.component


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.astroscoding.jokesapp.presentation.core.util.Screens
import com.astroscoding.jokesapp.presentation.screen.add_joke.AddJokeScreen
import com.astroscoding.jokesapp.presentation.screen.all_jokes.AllJokesScreen
import com.astroscoding.jokesapp.presentation.screen.christmas_jokes.ChristmasJokesScreen
import com.astroscoding.jokesapp.presentation.screen.dark_jokes.DarkJokesScreen
import com.astroscoding.jokesapp.presentation.screen.favourite_jokes.FavouriteJokesScreen
import com.astroscoding.jokesapp.presentation.screen.home.HomeScreen
import com.astroscoding.jokesapp.presentation.screen.misc_jokes.MiscJokeScreen
import com.astroscoding.jokesapp.presentation.screen.on_boarding.WelcomeScreen
import com.astroscoding.jokesapp.presentation.screen.programming_jokes.ProgrammingJokeScreen
import com.astroscoding.jokesapp.presentation.screen.pun_jokes.PunJokesScreen
import com.astroscoding.jokesapp.presentation.screen.spooky_jokes.SpookyJokesScreen
import com.astroscoding.jokesapp.presentation.screen.statistics.StatisticsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavController(
    startDestination: String,
    navController: NavHostController
) {

    AnimatedNavHost(navController = navController, startDestination = startDestination) {

        composable(
            Screens.WelcomeScreen.route
        ) {
            WelcomeScreen {
                navController.navigate(
                    Screens.HomeScreen.route
                ) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
        }

        composable(
            Screens.HomeScreen.route
        ) {
            HomeScreen(navController.currentBackStackEntryAsState()) { category ->
                navController.navigate(category.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
        composable(Screens.AllJokeScreen.route) {
            AllJokesScreen()
        }
        composable(Screens.ProgrammingJokeScreen.route) {
            ProgrammingJokeScreen()
        }
        composable(Screens.DarkJokeScreen.route) {
            DarkJokesScreen()
        }
        composable(Screens.DarkJokeScreen.route) {
            DarkJokesScreen()
        }
        composable(Screens.MiscJokeScreen.route) {
            MiscJokeScreen()
        }
        composable(Screens.PunJokeScreen.route) {
            PunJokesScreen()
        }
        composable(Screens.SpookyJokeScreen.route) {
            SpookyJokesScreen()
        }
        composable(Screens.Christmas.route) {
            ChristmasJokesScreen()
        }
        composable(Screens.FavouriteJokes.route) {
            FavouriteJokesScreen()
        }
        composable(Screens.Statistics.route) {
            StatisticsScreen(navController.currentBackStackEntryAsState()) { category ->
                navController.navigate(category.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
        composable(Screens.AddJoke.route) {
            AddJokeScreen()
        }

    }
}