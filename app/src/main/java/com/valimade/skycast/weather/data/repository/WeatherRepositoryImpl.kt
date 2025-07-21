package com.valimade.skycast.weather.data.repository

import android.util.Log
import com.valimade.skycast.weather.data.mapper.WeatherDataMapper
import com.valimade.skycast.weather.data.model.WeatherInformData
import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

class  WeatherRepositoryImpl(
    private val httpClient: HttpClient,
    private val mapper: WeatherDataMapper,
    private val apikey: String,
): WeatherRepository{

    override suspend fun realtimeWeather(location: String): WeatherInform? {
        return try {
            val jsonElement = httpClient.get("/v4/weather/realtime") {
                parameter("location", location)
                parameter("apikey", apikey)
            }.body<JsonObject>()["data"]

            val json = Json { ignoreUnknownKeys = true }

            val weatherInform = json.decodeFromJsonElement<WeatherInformData>(jsonElement!!)

            mapper.weatherInformDataToDomain(weatherInform)
        } catch (e: Exception) {
            Log.e("WeatherRepository", "realtimeWeather ${e.message}")
            null
        }
    }

    override suspend fun forecastWeather(location: String): List<WeatherInform>? {
        return try {
            val jsonElement = httpClient.get("/v4/weather/forecast") {
                parameter("location", location)
                parameter("apikey", apikey)
            }.body<JsonObject>()["timelines"]

            val json = Json { ignoreUnknownKeys = true }

            val hourlyList = jsonElement
                ?.jsonObject
                ?.get("hourly") ?: return null

            val weatherList = json.decodeFromJsonElement<List<WeatherInformData>>(hourlyList)

            weatherList.map{
                mapper.weatherInformDataToDomain(it)
            }
        } catch (e: Exception) {
            Log.e("WeatherRepository", "forecastWeather ${e.message}")
            null
        }
    }

}

