package com.valimade.skycast.weather.domain.repository

import com.valimade.skycast.weather.domain.model.realtime.WeatherResponse

interface WeatherRepository {
    suspend fun realtimeWeather(location: String): WeatherResponse?
}