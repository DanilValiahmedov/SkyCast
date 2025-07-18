package com.valimade.skycast.location.domain.usecase

import com.valimade.skycast.location.domain.model.LocationResponse
import com.valimade.skycast.location.domain.model.PermissionException
import com.valimade.skycast.location.domain.model.PermissionResult
import com.valimade.skycast.location.domain.repository.LocationRepository

class GetLocationUseCase(
    private val getPermissionUseCase: GetPermissionUseCase,
    private val repository: LocationRepository
) {
    suspend fun invoke(): Result<LocationResponse> {
        return when(getPermissionUseCase.invoke()) {
            is PermissionResult.Success -> repository.getLocation()
            is PermissionResult.Failure -> Result.failure(PermissionException())
        }
    }
}