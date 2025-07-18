package com.valimade.skycast.permission.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.valimade.skycast.permission.domain.repository.PermissionRepository

class PermissionRepositoryImpl(
    val context: Context,
): PermissionRepository {

    override suspend fun getLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED
    }

}