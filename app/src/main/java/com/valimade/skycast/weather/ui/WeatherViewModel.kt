package com.valimade.skycast.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.skycast.geocoding.domain.usecase.ReverseGeocodingUseCase
import com.valimade.skycast.geocoding.ui.mapper.GeocodingUIMapper
import com.valimade.skycast.location.domain.model.LocationException
import com.valimade.skycast.location.domain.usecase.GetLocationUseCase
import com.valimade.skycast.permission.domain.model.PermissionException
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
    private val getLocationUseCase: GetLocationUseCase,
) : ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherScreenState())

    val weatherState: StateFlow<WeatherScreenState> = _weatherState

    init {
        getLocation()
    }

    fun getPermission(isPermission: Boolean) {
        _weatherState.update {
            it.copy(
                isPermission = isPermission,
            )
        }
        if (isPermission) {
            getLocation()
        }
    }

    fun getLocation() {
        _weatherState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            val locationResult = getLocationUseCase()

            locationResult
                .onSuccess { location ->
                    _weatherState.update {
                        it.copy(location = location)
                    }

                    if (_weatherState.value.isFirstLaunch) {
                        startApp()
                    } else {
                        _weatherState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
                .onFailure { e ->
                    when (e) {
                        is PermissionException -> _weatherState.update {
                            it.copy(
                                isLoading = false,
                                isPermission = false
                            )
                        }

                        is LocationException -> _weatherState.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = "Ошибка при получении геолокации",
                            )
                        }

                        else -> _weatherState.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = "Неизвестная ошибка: ${e.message}",
                            )
                        }
                    }
                }

        }
    }

    fun startApp() {
        viewModelScope.launch {
            getRealtimeWeather()
            getForecastWeather()
            getReverseGeocoding()

            _weatherState.update {
                it.copy(
                    isLoading = false,
                    isFirstLaunch = false,
                )
            }
        }

    }

    fun replayApp() {
        _weatherState.update {
            it.copy(
                isLoading = true,
            )
        }
        if (checkValidLocation()) {
            viewModelScope.launch {
                getRealtimeWeather()
                getForecastWeather()
                getReverseGeocoding()

                _weatherState.update {
                    it.copy(
                        isLoading = false,
                    )
                }
            }
        } else {
            _weatherState.update {
                it.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = "Ошибка при повторном запросе",
                )
            }
        }
    }

    fun clickCurrentWeather() {
        _weatherState.update {
            it.copy(
                isCurrentWeather = !_weatherState.value.isCurrentWeather
            )
        }
    }

    fun clickForecastWeather() {
        _weatherState.update {
            it.copy(
                isForecastWeather = !_weatherState.value.isForecastWeather
            )
        }
    }

    private suspend fun getRealtimeWeather() {
        if (checkValidLocation()) {
            val location =
                "${_weatherState.value.location!!.lat!!},${_weatherState.value.location!!.lon!!}"
            val weatherDomain = realtimeWeatherUseCase(location)
            if (weatherDomain != null) {

                val baseWeather = weatherMapper.baseWeatherDomainToUI(weatherDomain)
                val additionalWeather = weatherMapper.additionalWeatherDomainToUI(weatherDomain)
                val dateAndTime = weatherMapper.dateAndTimeDomainToUI(weatherDomain)

                _weatherState.update {
                    it.copy(
                        baseWeatherRealtime = baseWeather,
                        additionalWeatherRealtime = additionalWeather,
                        dateAndTime = dateAndTime,
                    )
                }

            } else {
                _weatherState.update {
                    it.copy(
                        isError = true,
                        errorMessage = "Ошибка при получении информации о погоде",
                    )
                }
            }

        } else {
            _weatherState.update {
                it.copy(
                    isError = true,
                    errorMessage = "Ошибка при получении геолокации",
                )
            }
        }
    }

    private suspend fun getForecastWeather() {
        if (checkValidLocation()) {
            val location =
                "${_weatherState.value.location!!.lat!!},${_weatherState.value.location!!.lon!!}"
            val forecastDomain = forecastWeatherUseCase(location)
            if (forecastDomain != null) {

                val forecastUI = forecastDomain.map {
                    weatherMapper.forecastListDomainToUI(it)
                }
                _weatherState.update {
                    it.copy(
                        forecastList = forecastUI,
                    )
                }

            } else {
                _weatherState.update {
                    it.copy(
                        isError = true,
                        errorMessage = "Ошибка при получения прогноза погоды",
                    )
                }
            }
        } else {
            _weatherState.update {
                it.copy(
                    isError = true,
                    errorMessage = "Ошибка при получении геолокации",
                )
            }
        }
    }

    private suspend fun getReverseGeocoding() {
        if (checkValidLocation()) {
            val locationDomain = reverseGeocodingUseCase(
                lat = _weatherState.value.location!!.lat!!,
                lon = _weatherState.value.location!!.lon!!,
            )
            if (locationDomain != null) {

                val locationUI = geocodingMapper.propertiesDomainToUI(locationDomain)
                _weatherState.update {
                    it.copy(
                        locationGeocoding = locationUI,
                    )
                }

            } else {
                _weatherState.update {
                    it.copy(
                        isError = true,
                        errorMessage = "Ошибка при геокодировании вашего местоположения",
                    )
                }
            }
        } else {
            _weatherState.update {
                it.copy(
                    isError = true,
                    errorMessage = "Ошибка при получении геолокации",
                )
            }
        }
    }

    private fun checkValidLocation(): Boolean {
        val location = _weatherState.value.location
        return location?.lat != null && location.lon != null
    }

}