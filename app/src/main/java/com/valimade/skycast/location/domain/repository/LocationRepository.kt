package com.valimade.skycast.location.domain.repository

import com.valimade.skycast.location.domain.model.LocationResponse

interface LocationRepository {
    suspend fun getLocation(): Result<LocationResponse>
}