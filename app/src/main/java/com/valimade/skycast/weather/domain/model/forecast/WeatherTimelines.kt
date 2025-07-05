package com.valimade.skycast.weather.domain.model.forecast

import com.valimade.skycast.weather.domain.model.WeatherInform

data class WeatherTimelines(
    val daily: List<WeatherInform>,
    val hourly: List<WeatherInform>,
)