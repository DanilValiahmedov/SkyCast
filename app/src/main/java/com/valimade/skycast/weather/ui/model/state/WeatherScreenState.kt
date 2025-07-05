package com.valimade.skycast.weather.ui.model.state

import com.valimade.skycast.weather.ui.model.forecast.WeatherForecastUI
import com.valimade.skycast.weather.ui.model.realtime.WeatherRealtimeUI

data class WeatherScreenState (
    val isLoading: Boolean = true,
    val weatherForecast: WeatherForecastUI = WeatherForecastUI(),
    val weatherRealtime: WeatherRealtimeUI = WeatherRealtimeUI(),
    val isError: Boolean = false,
)