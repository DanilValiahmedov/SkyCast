package com.valimade.skycast.geocoding.domain.repository

import com.valimade.skycast.geocoding.domain.model.GeocodingProperties

interface GeocodingRepository {
    suspend fun reverseGeocoding(lat: Double, lon: Double): GeocodingProperties?
    suspend fun forwardGeocoding(text: String): List<GeocodingProperties>?
}
