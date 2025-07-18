package com.valimade.skycast.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.skycast.geocoding.domain.usecase.ReverseGeocodingUseCase
import com.valimade.skycast.geocoding.ui.mapper.GeocodingUIMapper
import com.valimade.skycast.weather.domain.usecase.ForecastWeatherUseCase
import com.valimade.skycast.weather.domain.usecase.RealtimeWeatherUseCase
import com.valimade.skycast.weather.ui.mapper.WeatherUIMapper
import com.valimade.skycast.weather.ui.model.WeatherScreenState
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

                val baseWeather = weatherMapper.baseWeatherDomainToUI(weatherDomain)
                val additionalWeather = weatherMapper.additionalWeatherDomainToUI(weatherDomain)
                val dateAndTime = weatherMapper.dateAndTimeDomainToUI(weatherDomain)

                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        baseWeatherRealtime = baseWeather,
                        additionalWeatherRealtime = additionalWeather,
                        dateAndTime = dateAndTime,
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
            val forecastDomain = forecastWeatherUseCase(location)
            if(forecastDomain != null) {

                val forecastUI = forecastDomain.map {
                    weatherMapper.forecastListDomainToUI(it)
                }
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        forecastList = forecastUI,
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

            val locationDomain = reverseGeocodingUseCase(lat, lon)
            if(locationDomain != null) {

                val locationUI = geocodingMapper.propertiesDomainToUI(locationDomain)
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        locationGeocoding = locationUI,
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