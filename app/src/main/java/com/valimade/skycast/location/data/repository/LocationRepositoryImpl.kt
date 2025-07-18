package com.valimade.skycast.location.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.valimade.skycast.location.domain.repository.LocationRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.google.android.gms.location.LocationServices
import com.valimade.skycast.location.domain.model.LocationResponse
import com.valimade.skycast.location.domain.model.Result

class LocationRepositoryImpl(
    private val context: Context,
): LocationRepository {

    override suspend fun getLocation(): LocationResponse {
        val checkPermission = ContextCompat.checkSelfPermission(
            context,Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED

        return if(checkPermission) {
            suspendCoroutine { cont ->
                val client = LocationServices.getFusedLocationProviderClient(context)
                client.lastLocation
                    .addOnSuccessListener {
                        cont.resume(
                            if (it != null) {
                                LocationResponse(
                                    result = Result.SUCCESSFUL,
                                    lat = it.latitude,
                                    lon = it.longitude,
                                )
                            } else {
                                LocationResponse(
                                    result = Result.ERROR,
                                )
                            }
                        )}
                    .addOnFailureListener { cont.resume(
                        LocationResponse(
                            result = Result.ERROR,
                        )
                    ) }
            }
        } else {
            LocationResponse(
                result = Result.NOTPERMISSION,
            )
        }

    }
}