package com.astroscoding.jokesapp.presentation.core.util

import androidx.annotation.DrawableRes
import com.astroscoding.jokesapp.R

sealed class Screens(val route: String, @DrawableRes val icon: Int = 0, val description: String = ""){

    fun capitalizeFirst() =  route[0].uppercase()+route.substring(1)

    object HomeScreen : Screens("home")
    object AllJokeScreen : Screens("all", R.drawable.ic_misc, "A mixture of laughter")
    object ProgrammingJokeScreen : Screens("programming", R.drawable.ic_code, "Some bugs better left uncaught")
    object MiscJokeScreen : Screens("misc", R.drawable.ic_all, "Neatly mixed gags")
    object DarkJokeScreen : Screens("dark",  R.drawable.ic_dark, "We know, it doesn't always shine")
    object PunJokeScreen : Screens("pun", R.drawable.ic_pun, "How linguistic are you?")
    object SpookyJokeScreen : Screens("spooky", R.drawable.ic_spooky, "One sweet day of scares!")
    object Christmas : Screens("christmas", R.drawable.ic_christmas, "Ho Ho Ho!")
    object FavouriteJokes : Screens("favourite")
    object AddJoke : Screens("add")
    object Statistics : Screens("statistics")
}
