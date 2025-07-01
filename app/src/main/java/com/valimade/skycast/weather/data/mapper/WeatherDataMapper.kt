package com.valimade.skycast.weather.data.mapper

import com.valimade.skycast.weather.data.model.realtime.WeatherData
import com.valimade.skycast.weather.data.model.realtime.WeatherResponseData
import com.valimade.skycast.weather.data.model.realtime.WeatherValuesData
import com.valimade.skycast.weather.domain.model.realtime.Weather
import com.valimade.skycast.weather.domain.model.realtime.WeatherResponse
import com.valimade.skycast.weather.domain.model.realtime.WeatherValues

object WeatherDataMapper {

    fun weatherResponseDataToDomain(weatherResponse: WeatherResponseData): WeatherResponse {
        return WeatherResponse(
            data = weatherDataToDomain(weatherResponse.data),
        )
    }

    fun weatherResponseDomainToData(weatherResponse: WeatherResponse): WeatherResponseData {
        return WeatherResponseData(
            data = weatherDomainToData(weatherResponse.data),
        )
    }

    private fun weatherDataToDomain(weather: WeatherData): Weather {
        return Weather(
            time = weather.time,
            values = weatherValuesDataToDomain(weather.values),
        )
    }

    private fun weatherDomainToData(weather: Weather): WeatherData {
        return WeatherData(
            time = weather.time,
            values = weatherValuesDomainToData(weather.values),
        )
    }

    private fun weatherValuesDataToDomain(weatherValues: WeatherValuesData): WeatherValues {
        return WeatherValues(
            cloudBase = weatherValues.cloudBase,
            cloudCeiling = weatherValues.cloudCeiling,
            cloudCover = weatherValues.cloudCover,
            dewPoint = weatherValues.dewPoint,
            freezingRainIntensity = weatherValues.freezingRainIntensity,
            humidity = weatherValues.humidity,
            precipitationProbability = weatherValues.precipitationProbability,
            pressureSurfaceLevel = weatherValues.pressureSurfaceLevel,
            rainIntensity = weatherValues.rainIntensity,
            sleetIntensity = weatherValues.sleetIntensity,
            snowIntensity = weatherValues.snowIntensity,
            temperature = weatherValues.temperature,
            temperatureApparent = weatherValues.temperatureApparent,
            uvHealthConcern = weatherValues.uvHealthConcern,
            uvIndex = weatherValues.uvIndex,
            visibility = weatherValues.visibility,
            weatherCode = weatherValues.weatherCode,
            windDirection = weatherValues.windDirection,
            windGust = weatherValues.windGust,
            windSpeed = weatherValues.windSpeed,
        )
    }

    private fun weatherValuesDomainToData(weatherValues: WeatherValues): WeatherValuesData {
        return WeatherValuesData(
            cloudBase = weatherValues.cloudBase,
            cloudCeiling = weatherValues.cloudCeiling,
            cloudCover = weatherValues.cloudCover,
            dewPoint = weatherValues.dewPoint,
            freezingRainIntensity = weatherValues.freezingRainIntensity,
            humidity = weatherValues.humidity,
            precipitationProbability = weatherValues.precipitationProbability,
            pressureSurfaceLevel = weatherValues.pressureSurfaceLevel,
            rainIntensity = weatherValues.rainIntensity,
            sleetIntensity = weatherValues.sleetIntensity,
            snowIntensity = weatherValues.snowIntensity,
            temperature = weatherValues.temperature,
            temperatureApparent = weatherValues.temperatureApparent,
            uvHealthConcern = weatherValues.uvHealthConcern,
            uvIndex = weatherValues.uvIndex,
            visibility = weatherValues.visibility,
            weatherCode = weatherValues.weatherCode,
            windDirection = weatherValues.windDirection,
            windGust = weatherValues.windGust,
            windSpeed = weatherValues.windSpeed,
        )
    }

}
