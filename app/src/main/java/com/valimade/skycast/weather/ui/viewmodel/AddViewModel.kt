package com.valimade.skycast.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.skycast.geocoding.domain.usecase.ForwardGeocodingUseCase
import com.valimade.skycast.geocoding.ui.mapper.GeocodingUIMapper
import com.valimade.skycast.weather.ui.model.state.AddScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddViewModel(
    private val forwardGeocodingUseCase: ForwardGeocodingUseCase,
    private val geocodingMapper: GeocodingUIMapper,
): ViewModel() {

    private val _addState = MutableStateFlow(AddScreenState())
    val addState: StateFlow<AddScreenState> = _addState

    fun changeRequestText(newText: String) {
        _addState.update {
            it.copy(
                requestText = newText
            )
        }
    }

    fun getForwardGeocoding() {
        if(_addState.value.requestText.length % 3 == 0) {
            _addState.update {
                it.copy(
                    isLoading = true,
                )
            }
            viewModelScope.launch {
                val forwardDomain = forwardGeocodingUseCase(text = _addState.value.requestText)
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
    }
}