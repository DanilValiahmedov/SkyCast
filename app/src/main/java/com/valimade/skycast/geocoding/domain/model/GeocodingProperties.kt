package com.valimade.skycast.geocoding.domain.model

data class GeocodingProperties(
    val country: String,
    val state: String,
    val city: String,
    val coordinates: List<Double> = emptyList(),
)