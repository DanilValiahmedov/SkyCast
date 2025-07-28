package com.valimade.skycast.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.valimade.skycast.geocoding.domain.usecase.ForwardGeocodingUseCase
import com.valimade.skycast.geocoding.ui.mapper.GeocodingUIMapper
import com.valimade.skycast.weather.ui.model.state.AddScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddViewModel(
    private val forwardGeocodingUseCase: ForwardGeocodingUseCase,
    private val geocodingMapper: GeocodingUIMapper,
): ViewModel() {

    private val _addState = MutableStateFlow(AddScreenState())
    val addState: StateFlow<AddScreenState> = _addState

    private suspend fun getForwardGeocoding(namePlaces: String) {
        _addState.update {
            it.copy(
                isLoading = true,
            )
        }
        val forwardDomain = forwardGeocodingUseCase(text = namePlaces)
        if (forwardDomain != null) {

            val forwardUI = forwardDomain.map{
                geocodingMapper.propertiesDomainToUI(it)
            }
            _addState.update {
                it.copy(
                    isLoading = false,
                    namePlaces = forwardUI,
                )
            }

        } else {
            _addState.update {
                it.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = "Ошибка при поиске адреса",
                )
            }
        }
    }
}