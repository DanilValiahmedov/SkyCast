package com.valimade.skycast.geocoding.domain.repository

import com.valimade.skycast.geocoding.domain.model.GeocodingReverse

interface GeocodingRepository {
    suspend fun reverseGeocoding(lat: Double, lon: Double): GeocodingReverse?
}
