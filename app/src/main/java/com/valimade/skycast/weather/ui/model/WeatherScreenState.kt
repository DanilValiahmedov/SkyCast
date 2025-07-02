package com.valimade.skycast.weather.ui.model

import com.valimade.skycast.weather.ui.model.realtime.WeatherResponseUI

data class WeatherScreenState (
    val isLoading: Boolean = true,
    val weather: WeatherResponseUI = WeatherResponseUI(),
    val isError: Boolean = false,
)