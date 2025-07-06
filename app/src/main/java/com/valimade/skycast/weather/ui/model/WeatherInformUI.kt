package com.valimade.skycast.weather.ui.model

data class WeatherInformUI(
    val date: String = "",
    val time: String = "",
    val values: WeatherValuesUI = WeatherValuesUI()
)
