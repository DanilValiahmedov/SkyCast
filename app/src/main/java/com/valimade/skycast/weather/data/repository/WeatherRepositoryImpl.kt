package com.valimade.skycast.weather.data.repository

import com.valimade.skycast.weather.data.mapper.WeatherDataMapper
import com.valimade.skycast.weather.data.mock.WeatherMock
import com.valimade.skycast.weather.data.model.WeatherInformData
import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.model.forecast.WeatherForecast
import com.valimade.skycast.weather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

class WeatherRepositoryImpl(
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
            null
        }
    }

    override suspend fun forecastWeather(location: String): WeatherForecast? {
        return try {
            //Реальная реализаиця
            /*
            val responseWeather = httpClient.get("/v4/weather/forecast") {
                parameter("location", location)
                parameter("apikey", apikey)
            }.body<WeatherForecastData>()
            */

            //Моковые данные
            val responseWeather = WeatherMock.responseForecast

            mapper.weatherForecastDataToDomain(responseWeather)
        } catch (e: Exception) {
            null
        }
    }

}

