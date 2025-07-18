package com.valimade.skycast.location.domain.usecase

import com.valimade.skycast.location.domain.model.PermissionResult
import com.valimade.skycast.location.domain.repository.LocationRepository

class GetPermissionUseCase(private val repository: LocationRepository) {
    suspend fun invoke(): PermissionResult {
        return if(repository.getPermission()) PermissionResult.Success
            else PermissionResult.Failure
    }
}