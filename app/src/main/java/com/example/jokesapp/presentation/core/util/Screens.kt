package com.example.jokesapp.presentation.core.util

sealed class Screens(val route: String){
    object HomeScreen : Screens("home")
    object AllJokeScreen : Screens("all")
    object ProgrammingJokeScreen : Screens("programming")
    object MiscJokeScreen : Screens("misc")
    object DarkJokeScreen : Screens("dark")
    object PunJokeScreen : Screens("pun")
    object SpookyJokeScreen : Screens("spooky")
    object Christmas : Screens("christmas")
    object FavouriteJokes : Screens("favourite")
    object AddJoke : Screens("add")
    object Statistics : Screens("statistics")
}
