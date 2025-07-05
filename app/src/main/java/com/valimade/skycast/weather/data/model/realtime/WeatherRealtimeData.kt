package com.valimade.skycast.weather.data.model.realtime

import com.valimade.skycast.weather.data.model.WeatherInformData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeatherRealtimeData(
    @SerialName("data") val data: WeatherInformData,
)