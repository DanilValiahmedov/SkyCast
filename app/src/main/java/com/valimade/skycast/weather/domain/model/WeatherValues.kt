package com.valimade.skycast.weather.domain.model

data class WeatherValues(
    val cloudBase: Double? = null,
    val cloudCeiling: Double? = null,
    val cloudCover: Int? = null,
    val dewPoint: Double? = null,
    val freezingRainIntensity: Int? = null,
    val humidity: Int? = null,
    val precipitationProbability: Int? = null,
    val pressureSurfaceLevel: Double? = null,
    val sleetIntensity: Int? = null,
    val snowIntensity: Int? = null,
    val temperature: Double? = null,
    val temperatureApparent: Double? = null,
    val uvHealthConcern: Int? = null,
    val uvIndex: Int? = null,
    val visibility: Double? = null,
    val weatherCode: Int? = null,
    val windDirection: Int? = null,
    val windGust: Double? = null,
    val windSpeed: Double? = null,
)
