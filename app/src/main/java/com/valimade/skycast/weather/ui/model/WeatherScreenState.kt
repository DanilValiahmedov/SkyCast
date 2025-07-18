package com.valimade.skycast.weather.ui.model

import com.valimade.skycast.weather.ui.model.forecat.ForecastList
import com.valimade.skycast.weather.ui.model.realtime.AdditionalWeather
import com.valimade.skycast.weather.ui.model.realtime.BaseWeather

data class WeatherScreenState (
    val isLoading: Boolean = true,
    val baseWeatherRealtime: List<BaseWeather> = emptyList(),
    val additionalWeatherRealtime: List<AdditionalWeather> = emptyList(),
    val forecastList: List<ForecastList> = emptyList(),
    val dateAndTime: String = "",
    val locationGeocoding: String = "",
    val isError: Boolean = false,
)