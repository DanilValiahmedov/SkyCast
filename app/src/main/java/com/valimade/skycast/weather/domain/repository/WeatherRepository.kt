package com.valimade.skycast.weather.domain.repository

import com.valimade.skycast.weather.domain.model.WeatherInform

interface WeatherRepository {
    suspend fun realtimeWeather(location: String): WeatherInform?
    suspend fun forecastWeather(location: String): List<WeatherInform>?
}