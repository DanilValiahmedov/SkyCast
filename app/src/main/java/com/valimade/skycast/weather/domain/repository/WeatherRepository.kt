package com.valimade.skycast.weather.domain.repository

import com.valimade.skycast.weather.domain.model.forecast.WeatherForecast
import com.valimade.skycast.weather.domain.model.realtime.WeatherRealtime

interface WeatherRepository {
    suspend fun realtimeWeather(location: String): WeatherRealtime?
    suspend fun forecastWeather(location: String): WeatherForecast?
}