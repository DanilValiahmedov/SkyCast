package com.valimade.skycast.weather.ui.model.forecat

import androidx.compose.ui.graphics.Color

data class ForecastWeather(
    val icon: Int = 0,
    val name: String = "",
    val value: String = "",
    val units: String = "",
    val date: String = "",
    val time: String = "",
    val color: Color = Color.Black,
)