package com.valimade.skycast.geocoding.data.repository

import com.valimade.skycast.geocoding.data.mapper.GeocodingDataMapper
import com.valimade.skycast.geocoding.data.model.GeocodingPropertiesData
import com.valimade.skycast.geocoding.domain.model.GeocodingProperties
import com.valimade.skycast.geocoding.domain.repository.GeocodingRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class GeocodingRepositoryImpl(
    private val httpClient: HttpClient,
    private val mapper: GeocodingDataMapper,
    private val apiKey: String,
): GeocodingRepository {

    override suspend fun reverseGeocoding(
        lat: Double, lon: Double
    ): GeocodingProperties? {
        return try {
            val jsonElement = httpClient.get("/v1/geocode/reverse") {
                parameter("lat", lat)
                parameter("lon", lon)
                parameter("apiKey", apiKey)
            }.body<JsonObject>()["features"]

            val json = Json { ignoreUnknownKeys = true }

            val geocodingProperties = json.decodeFromJsonElement<GeocodingPropertiesData>(
                jsonElement ?.jsonArray
                    ?.firstOrNull()
                    ?.jsonObject
                    ?.get("properties") ?: error("No properties found")
            )

            mapper.propertiesDataToDomain(geocodingProperties)
        } catch(e: Exception) {
            null
        }
    }

}