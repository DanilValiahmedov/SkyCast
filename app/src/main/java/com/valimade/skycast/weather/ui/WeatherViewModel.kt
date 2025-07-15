package com.valimade.skycast.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.skycast.geocoding.domain.usecase.ReverseGeocodingUseCase
import com.valimade.skycast.geocoding.ui.mapper.GeocodingUIMapper
import com.valimade.skycast.weather.domain.usecase.ForecastWeatherUseCase
import com.valimade.skycast.weather.domain.usecase.RealtimeWeatherUseCase
import com.valimade.skycast.weather.ui.mapper.WeatherUIMapper
import com.valimade.skycast.weather.ui.model.state.WeatherScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val realtimeWeatherUseCase: RealtimeWeatherUseCase,
    private val forecastWeatherUseCase: ForecastWeatherUseCase,
    private val reverseGeocodingUseCase: ReverseGeocodingUseCase,
    private val weatherMapper: WeatherUIMapper,
    private val geocodingMapper: GeocodingUIMapper,
): ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherScreenState())

    val weatherState: StateFlow<WeatherScreenState> = _weatherState

    fun getRealtimeWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherState.update {
                it.copy(
                    isLoading = true,
                )
            }

            val location = "$lat,$lon"
            val weatherDomain = realtimeWeatherUseCase(location)
            if(weatherDomain != null) {

                val weatherUI = weatherMapper.weatherInformDomainToUI(weatherDomain)
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        weatherRealtime = weatherUI,
                    )
                }

            } else {
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }

        }
    }

    fun getForecastWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherState.update {
                it.copy(
                    isLoading = true,
                )
            }

            val location = "$lat,$lon"
            val weatherDomain = forecastWeatherUseCase(location)
            if(weatherDomain != null) {

                val weatherUI = weatherMapper.weatherForecastDomainToUI(weatherDomain)
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        weatherForecast = weatherUI,
                    )
                }

            } else {
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }

        }
    }

    fun getReverseGeocoding(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherState.update {
                it.copy(
                    isLoading = true,
                )
            }

            val geocodingDomain = reverseGeocodingUseCase(lat, lon)
            if(geocodingDomain != null) {

                val geocodingUI = geocodingMapper.propertiesDomainToUI(geocodingDomain)
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        geocodingProperties = geocodingUI
                    )
                }

            } else {
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }

        }
    }

}