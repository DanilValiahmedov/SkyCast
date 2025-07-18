package com.valimade.skycast.location.domain.usecase

import com.valimade.skycast.location.domain.model.LocationResponse
import com.valimade.skycast.location.domain.repository.LocationRepository

class GetLocationUseCase(private val repository: LocationRepository) {
    suspend fun invoke(): LocationResponse {
        return repository.getLocation()
    }
}