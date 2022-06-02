package com.astroscoding.jokesapp.presentation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

object Dimension {

    @Composable
    fun Float.width() : Float{
        val config = LocalConfiguration.current
        return (this * config.screenWidthDp) / 100
    }
}