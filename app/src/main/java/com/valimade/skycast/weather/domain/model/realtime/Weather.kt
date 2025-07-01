package com.valimade.skycast.weather.domain.model.realtime

data class Weather(
    val time: String,
    val values: WeatherValues
)
