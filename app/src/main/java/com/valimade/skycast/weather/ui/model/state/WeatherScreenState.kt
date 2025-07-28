package com.valimade.skycast.weather.ui.model.state

import com.valimade.skycast.location.domain.model.LocationResponse
import com.valimade.skycast.weather.ui.model.forecat.ForecastList
import com.valimade.skycast.weather.ui.model.realtime.AdditionalWeather
import com.valimade.skycast.weather.ui.model.realtime.BaseWeather

data class WeatherScreenState (
    val isLoading: Boolean = true,
    val isFirstLaunch: Boolean = true,
    val isPermission: Boolean = true,
    val location: LocationResponse? = null,

    val baseWeatherRealtime: List<BaseWeather> = emptyList(),
    val additionalWeatherRealtime: List<AdditionalWeather> = emptyList(),

    val forecastList: List<ForecastList> = emptyList(),

    val dateAndTime: String = "",
    val locationGeocoding: String = "",

    val showCurrentWeather: Boolean = false,
    val showForecastWeather: Boolean = false,

    val isError: Boolean = false,
    val errorMessage: String = "Произошла ошибка",
)