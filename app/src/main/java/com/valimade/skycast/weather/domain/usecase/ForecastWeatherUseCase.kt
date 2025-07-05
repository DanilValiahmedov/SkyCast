package com.valimade.skycast.weather.domain.usecase

import com.valimade.skycast.weather.domain.model.forecast.WeatherForecast
import com.valimade.skycast.weather.domain.repository.WeatherRepository

class ForecastWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(location: String): WeatherForecast? {
        return repository.forecastWeather(location)
    }
}