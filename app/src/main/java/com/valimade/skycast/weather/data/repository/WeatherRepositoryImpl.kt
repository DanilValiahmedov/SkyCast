package com.valimade.skycast.weather.data.repository

import com.valimade.skycast.weather.data.mapper.WeatherDataMapper
import com.valimade.skycast.weather.data.model.realtime.WeatherResponseData
import com.valimade.skycast.weather.domain.model.realtime.WeatherResponse
import com.valimade.skycast.weather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherRepositoryImpl(
    private val httpClient: HttpClient,
    private val mapper: WeatherDataMapper,
    private val apikey: String,
): WeatherRepository{

    override suspend fun realtimeWeather(location: String): WeatherResponse? {
        return try {
            val responseWeather = httpClient.get("/v4/weather/realtime") {
                parameter("location", location)
                parameter("apikey", apikey)
            }.body<WeatherResponseData>()

             mapper.weatherResponseDataToDomain(responseWeather)
        } catch (e: Exception) {
            null
        }
    }

}