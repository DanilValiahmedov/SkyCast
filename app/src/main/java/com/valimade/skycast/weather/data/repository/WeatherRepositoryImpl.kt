package com.valimade.skycast.weather.data.repository

import com.valimade.skycast.weather.data.mapper.WeatherDataMapper
import com.valimade.skycast.weather.data.mock.WeatherMock
import com.valimade.skycast.weather.data.model.forecast.WeatherForecastData
import com.valimade.skycast.weather.data.model.realtime.WeatherRealtimeData
import com.valimade.skycast.weather.domain.model.forecast.WeatherForecast
import com.valimade.skycast.weather.domain.model.realtime.WeatherRealtime
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

    override suspend fun realtimeWeather(location: String): WeatherRealtime? {
        return try {
            //Реальная реализаиця
            /*
            val responseWeather = httpClient.get("/v4/weather/realtime") {
                parameter("location", location)
                parameter("apikey", apikey)
            }.body<WeatherRealtimeData>()

             */

            //Моковые данные
            val responseWeather = WeatherMock.responseRealtime

             mapper.weatherRealtimeDataToDomain(responseWeather)
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

