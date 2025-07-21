package com.valimade.skycast.geocoding.domain.usecase

import com.valimade.skycast.geocoding.domain.model.GeocodingProperties
import com.valimade.skycast.geocoding.domain.repository.GeocodingRepository

class ForwardGeocodingUseCase(private val repository: GeocodingRepository) {
    suspend operator fun invoke(text: String): List<GeocodingProperties>? {
        return repository.forwardGeocoding(text)
    }
}