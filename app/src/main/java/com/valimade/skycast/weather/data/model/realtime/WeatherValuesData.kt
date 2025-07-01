package com.valimade.skycast.weather.data.model.realtime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherValuesData(
    @SerialName("cloudBase") val cloudBase: Double,
    @SerialName("cloudCeiling") val cloudCeiling: Double,
    @SerialName("cloudCover") val cloudCover: Int,
    @SerialName("dewPoint") val dewPoint: Double,
    @SerialName("freezingRainIntensity") val freezingRainIntensity: Int,
    @SerialName("humidity") val humidity: Int,
    @SerialName("precipitationProbability") val precipitationProbability: Int,
    @SerialName("pressureSurfaceLevel") val pressureSurfaceLevel: Double,
    @SerialName("rainIntensity") val rainIntensity: Int,
    @SerialName("sleetIntensity") val sleetIntensity: Int,
    @SerialName("snowIntensity") val snowIntensity: Int,
    @SerialName("temperature") val temperature: Double,
    @SerialName("temperatureApparent") val temperatureApparent: Double,
    @SerialName("uvHealthConcern") val uvHealthConcern: Int,
    @SerialName("uvIndex") val uvIndex: Int,
    @SerialName("visibility") val visibility: Double,
    @SerialName("weatherCode") val weatherCode: Int,
    @SerialName("windDirection") val windDirection: Int,
    @SerialName("windGust") val windGust: Double,
    @SerialName("windSpeed") val windSpeed: Double,
)