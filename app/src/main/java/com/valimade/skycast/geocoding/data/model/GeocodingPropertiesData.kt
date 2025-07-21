package com.valimade.skycast.geocoding.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingPropertiesData(
    @SerialName("country") val country: String,
    @SerialName("state") val state: String,
    @SerialName("city") val city: String,
    @SerialName("coordinates") val coordinates: List<Double> = emptyList(),
)