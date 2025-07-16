package com.valimade.skycast.weather.domain.usecase

import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.repository.WeatherRepository

class ForecastWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(location: String): List<WeatherInform>? {
        return repository.forecastWeather(location)
    }
}