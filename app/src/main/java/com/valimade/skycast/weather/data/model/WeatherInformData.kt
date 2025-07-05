package com.valimade.skycast.weather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherInformData(
    @SerialName("time") val time: String,
    @SerialName("values") val values: WeatherValuesData
)
