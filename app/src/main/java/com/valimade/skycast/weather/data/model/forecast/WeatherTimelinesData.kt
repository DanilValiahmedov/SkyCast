package com.valimade.skycast.weather.data.model.forecast

import com.valimade.skycast.weather.data.model.WeatherInformData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherTimelinesData(
    @SerialName("daily") val daily: List<WeatherInformData>,
    @SerialName("hourly") val hourly: List<WeatherInformData>,
)