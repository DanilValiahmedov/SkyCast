package com.valimade.skycast.weather.data.model.forecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastData(
    @SerialName("timelines") val timelines: WeatherTimelinesData,
)