package com.valimade.skycast.permission.domain.repository

interface PermissionRepository {
    suspend fun getLocationPermission(): Boolean
}