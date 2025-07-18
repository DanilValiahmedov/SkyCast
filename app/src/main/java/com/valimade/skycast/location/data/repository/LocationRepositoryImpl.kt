package com.valimade.skycast.location.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.valimade.skycast.location.domain.repository.LocationRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.google.android.gms.location.LocationServices
import com.valimade.skycast.location.domain.model.LocationException
import com.valimade.skycast.location.domain.model.LocationResponse

class LocationRepositoryImpl(
    private val context: Context,
): LocationRepository {

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