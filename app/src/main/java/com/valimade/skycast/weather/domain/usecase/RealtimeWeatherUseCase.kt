package com.valimade.skycast.weather.domain.usecase

import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.repository.WeatherRepository

class RealtimeWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(location: String): WeatherInform? {
        return repository.realtimeWeather(location)
    }
}