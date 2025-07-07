package com.valimade.skycast.geocoding.data.repository

import com.valimade.skycast.geocoding.data.mapper.GeocodingDataMapper
import com.valimade.skycast.geocoding.data.model.GeocodingReverseData
import com.valimade.skycast.geocoding.domain.model.GeocodingReverse
import com.valimade.skycast.geocoding.domain.repository.GeocodingRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GeocodingRepositoryImpl(
    private val httpClient: HttpClient,
    private val mapper: GeocodingDataMapper,
    private val apiKey: String,
): GeocodingRepository {

    override suspend fun reverseGeocoding(
        lat: Double, lon: Double
    ): GeocodingReverse? {
        return try {
            val response = httpClient.get("/v1/geocode/reverse") {
                parameter("lat", lat)
                parameter("lon", lon)
                parameter("apiKey", apiKey)
            }.body<GeocodingReverseData>()

            mapper.reverseDataToDomain(response)
        } catch(e: Exception) {
            null
        }
    }

}