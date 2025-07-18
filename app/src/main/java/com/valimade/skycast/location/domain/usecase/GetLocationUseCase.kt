package com.valimade.skycast.location.domain.usecase

import com.valimade.skycast.location.domain.model.LocationResponse
import com.valimade.skycast.permission.domain.model.PermissionException
import com.valimade.skycast.permission.domain.model.PermissionResult
import com.valimade.skycast.location.domain.repository.LocationRepository
import com.valimade.skycast.permission.domain.usecase.GetLocationPermissionUseCase

class GetLocationUseCase(
    private val getLocationPermissionUseCase: GetLocationPermissionUseCase,
    private val repository: LocationRepository
) {
    suspend fun invoke(): Result<LocationResponse> {
        return when(getLocationPermissionUseCase.invoke()) {
            is PermissionResult.Success -> repository.getLocation()
            is PermissionResult.Failure -> Result.failure(PermissionException())
        }
    }
}