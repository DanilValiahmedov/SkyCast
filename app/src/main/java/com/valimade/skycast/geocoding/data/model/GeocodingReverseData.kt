package com.valimade.skycast.geocoding.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingReverseData(
    @SerialName("features") val features: List<GeocodingFeaturesData>,
)