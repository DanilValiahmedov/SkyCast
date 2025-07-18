package com.valimade.skycast.location.domain.model

data class LocationResponse(
    val result: Result,
    val lat: Double? = null,
    val lon: Double? = null,
)
