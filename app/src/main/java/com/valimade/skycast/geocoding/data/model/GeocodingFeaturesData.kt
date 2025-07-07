package com.valimade.skycast.geocoding.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingFeaturesData(
    @SerialName("properties") val properties: GeocodingPropertiesData,
)