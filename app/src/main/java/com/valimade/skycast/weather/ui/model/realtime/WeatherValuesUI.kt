package com.valimade.skycast.weather.ui.model.realtime

data class WeatherValuesUI(
    val cloudBase: Double? = 0.0,
    val cloudCeiling: Double? = 0.0,
    val cloudCover: Int? = 0,
    val dewPoint: Double? = 0.0,
    val freezingRainIntensity: Int? = 0,
    val humidity: Int? = 0,
    val precipitationProbability: Int? = 0,
    val pressureSurfaceLevel: Double? = 0.0,
    val rainIntensity: Double? = 0.0,
    val sleetIntensity: Int? = 0,
    val snowIntensity: Int? = 0,
    val temperature: Double? = 0.0,
    val temperatureApparent: Double? = 0.0,
    val uvHealthConcern: Int? = 0,
    val uvIndex: Int? = 0,
    val visibility: Double? = 0.0,
    val weatherCode: Int? = 0,
    val windDirection: Int? = 0,
    val windGust: Double? = 0.0,
    val windSpeed: Double? = 0.0,
)
