package com.valimade.skycast.weather.ui.model.forecast

import com.valimade.skycast.weather.ui.model.WeatherInformUI

data class WeatherTimelinesUI(
    val hourly: List<WeatherInformUI> = emptyList(),
)