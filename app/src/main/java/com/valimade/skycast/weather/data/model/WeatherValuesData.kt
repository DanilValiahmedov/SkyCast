package com.valimade.skycast.weather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherValuesData(
    @SerialName("cloudBase") val cloudBase: Double? = null,
    @SerialName("cloudCeiling") val cloudCeiling: Double? = null,
    @SerialName("cloudCover") val cloudCover: Int? = null,
    @SerialName("dewPoint") val dewPoint: Double? = null,
    @SerialName("freezingRainIntensity") val freezingRainIntensity: Int? = null,
    @SerialName("humidity") val humidity: Int? = null,
    @SerialName("precipitationProbability") val precipitationProbability: Int? = null,
    @SerialName("pressureSurfaceLevel") val pressureSurfaceLevel: Double? = null,
    @SerialName("rainIntensity") val rainIntensity: Double? = null,
    @SerialName("sleetIntensity") val sleetIntensity: Int? = null,
    @SerialName("snowIntensity") val snowIntensity: Int? = null,
    @SerialName("temperature") val temperature: Double? = null,
    @SerialName("temperatureApparent") val temperatureApparent: Double? = null,
    @SerialName("uvHealthConcern") val uvHealthConcern: Int? = null,
    @SerialName("uvIndex") val uvIndex: Int? = null,
    @SerialName("visibility") val visibility: Double? = null,
    @SerialName("weatherCode") val weatherCode: Int? = null,
    @SerialName("windDirection") val windDirection: Int? = null,
    @SerialName("windGust") val windGust: Double? = null,
    @SerialName("windSpeed") val windSpeed: Double? = null,
)