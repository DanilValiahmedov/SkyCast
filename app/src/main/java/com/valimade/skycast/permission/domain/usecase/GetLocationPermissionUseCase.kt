package com.valimade.skycast.permission.domain.usecase

import com.valimade.skycast.permission.domain.model.PermissionResult
import com.valimade.skycast.permission.domain.repository.PermissionRepository

class GetLocationPermissionUseCase(private val repository: PermissionRepository) {
    suspend fun invoke(): PermissionResult {
        val result = repository.getLocationPermission()

        return if(result) PermissionResult.Success
            else PermissionResult.Failure
    }
}