package com.valimade.skycast.location.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.valimade.skycast.location.domain.repository.LocationRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.google.android.gms.location.LocationServices
import com.valimade.skycast.location.domain.model.LocationException
import com.valimade.skycast.location.domain.model.LocationResponse

class LocationRepositoryImpl(
    private val context: Context,
): LocationRepository {
    override suspend fun getPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): Result<LocationResponse> {
        return suspendCoroutine { cont ->
            val client = LocationServices.getFusedLocationProviderClient(context)
            client.lastLocation
                .addOnSuccessListener {
                    cont.resume(
                        if (it != null) {
                            Result.success(
                                LocationResponse(
                                    lat = it.latitude,
                                    lon = it.longitude,
                                )
                            )
                        } else {
                            Result.failure(LocationException())
                        }
                    )}
                .addOnFailureListener { cont.resume(
                    Result.failure(LocationException())
                ) }
        }
    }

}