package com.valimade.skycast.weather.domain.model.realtime

data class WeatherValues(
    val cloudBase: Double?,
    val cloudCeiling: Double?,
    val cloudCover: Int?,
    val dewPoint: Double?,
    val freezingRainIntensity: Int?,
    val humidity: Int?,
    val precipitationProbability: Int?,
    val pressureSurfaceLevel: Double?,
    val rainIntensity: Double?,
    val sleetIntensity: Int?,
    val snowIntensity: Int?,
    val temperature: Double?,
    val temperatureApparent: Double?,
    val uvHealthConcern: Int?,
    val uvIndex: Int?,
    val visibility: Double?,
    val weatherCode: Int?,
    val windDirection: Int?,
    val windGust: Double?,
    val windSpeed: Double?,
)
