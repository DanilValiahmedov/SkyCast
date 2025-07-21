package com.valimade.skycast.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.valimade.skycast.weather.ui.AddScreen
import com.valimade.skycast.weather.ui.WeatherScreen
import kotlinx.serialization.Serializable

@Serializable
data object Weather: NavKey

@Serializable
data object Add: NavKey

@Composable
fun AppNav() {

    val backStack = rememberNavBackStack(Weather)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                Weather -> NavEntry(key) { WeatherScreen() }
                Add -> NavEntry(key) { AddScreen() }
                else -> NavEntry(key) { Text("Unknown route") }
            }
        }
    )

}