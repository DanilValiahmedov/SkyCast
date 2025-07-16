package com.valimade.skycast.weather.ui.model.state

import com.valimade.skycast.geocoding.ui.model.GeocodingPropertiesUI
import com.valimade.skycast.weather.ui.model.WeatherInformUI

data class WeatherScreenState (
    val isLoading: Boolean = true,
    val forecastList: List<WeatherInformUI> = emptyList(),
    val weatherRealtime: WeatherInformUI = WeatherInformUI(),
    val geocodingProperties: GeocodingPropertiesUI = GeocodingPropertiesUI(),
    val isError: Boolean = false,
)