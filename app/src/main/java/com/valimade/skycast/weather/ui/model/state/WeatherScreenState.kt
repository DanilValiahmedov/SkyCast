package com.valimade.skycast.weather.ui.model.state

import com.valimade.skycast.geocoding.ui.model.GeocodingReverseUI
import com.valimade.skycast.weather.ui.model.WeatherInformUI
import com.valimade.skycast.weather.ui.model.forecast.WeatherForecastUI

data class WeatherScreenState (
    val isLoading: Boolean = true,
    val weatherForecast: WeatherForecastUI = WeatherForecastUI(),
    val weatherRealtime: WeatherInformUI = WeatherInformUI(),
    val geocodingReverse: GeocodingReverseUI = GeocodingReverseUI(),
    val isError: Boolean = false,
)