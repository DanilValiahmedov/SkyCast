package com.valimade.skycast.weather.ui.model

sealed class WeatherIntent {
    data class Permission(val isPermission: Boolean): WeatherIntent()
    object Replay: WeatherIntent()
    object CurrentWeather: WeatherIntent()
    object ForecastWeather: WeatherIntent()
}