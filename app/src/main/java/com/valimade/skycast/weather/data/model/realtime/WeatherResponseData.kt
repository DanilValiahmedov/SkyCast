package com.valimade.skycast.weather.data.model.realtime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeatherResponseData(
    @SerialName("data") val data: WeatherData,
)