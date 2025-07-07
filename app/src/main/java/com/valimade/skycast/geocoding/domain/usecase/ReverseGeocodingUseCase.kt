package com.valimade.skycast.geocoding.domain.usecase

import com.valimade.skycast.geocoding.domain.model.GeocodingReverse
import com.valimade.skycast.geocoding.domain.repository.GeocodingRepository

class ReverseGeocodingUseCase(private val repository: GeocodingRepository) {
    suspend operator fun invoke(lat: Double, lon: Double): GeocodingReverse? {
        return repository.reverseGeocoding(lat, lon)
    }
}