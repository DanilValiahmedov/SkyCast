package com.valimade.skycast.weather.data.model.realtime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    @SerialName("time") val time: String,
    @SerialName("values") val values: WeatherValuesData
)
