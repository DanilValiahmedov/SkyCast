package com.valimade.skycast.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.skycast.weather.domain.usecase.RealtimeWeatherUseCase
import com.valimade.skycast.weather.ui.mapper.WeatherUIMapper
import com.valimade.skycast.weather.ui.model.WeatherScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val realtimeWeatherUseCase: RealtimeWeatherUseCase,
    private val mapper: WeatherUIMapper,
): ViewModel() {

    private val _weather = MutableStateFlow(WeatherScreenState())

    val weather: StateFlow<WeatherScreenState> = _weather

    fun getRealtimeWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weather.update {
                it.copy(
                    isLoading = true,
                )
            }

            val location = "$lat,$lon"
            val weatherDomain = realtimeWeatherUseCase(location)
            if(weatherDomain != null) {

                val weatherUI = mapper.weatherResponseDomainToUI(weatherDomain)
                _weather.update {
                    it.copy(
                        isLoading = false,
                        weather = weatherUI,
                    )
                }

            } else {
                _weather.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }

        }
    }

}