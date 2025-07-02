package com.valimade.skycast.weather.ui.model.realtime

data class WeatherUI(
    val time: String = "",
    val values: WeatherValuesUI = WeatherValuesUI()
)
