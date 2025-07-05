package com.valimade.skycast.weather.ui.mapper

import com.valimade.skycast.weather.domain.model.realtime.Weather
import com.valimade.skycast.weather.domain.model.realtime.WeatherResponse
import com.valimade.skycast.weather.domain.model.realtime.WeatherValues
import com.valimade.skycast.weather.ui.model.realtime.WeatherResponseUI
import com.valimade.skycast.weather.ui.model.realtime.WeatherUI
import com.valimade.skycast.weather.ui.model.realtime.WeatherValuesUI
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object WeatherUIMapper {

    fun weatherResponseUIToDomain(weatherResponse: WeatherResponseUI): WeatherResponse {
        return WeatherResponse(
            data = weatherUIToDomain(weatherResponse.data),
        )
    }

    fun weatherResponseDomainToUI(weatherResponse: WeatherResponse): WeatherResponseUI {
        return WeatherResponseUI(
            data = weatherDomainToUI(weatherResponse.data),
        )
    }

    private fun weatherUIToDomain(weather: WeatherUI): Weather {
        return Weather(
            time = weather.time,
            values = weatherValuesUIToDomain(weather.values),
        )
    }

    private fun weatherDomainToUI(weather: Weather): WeatherUI {
        return WeatherUI(
            time = timeFormat(weather.time),
            values = weatherValuesDomainToUI(weather.values),
        )
    }

    private fun timeFormat(time: String): String{
        return try {
            val instant = Instant.parse(time)
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                .withZone(ZoneId.systemDefault())
            formatter.format(instant)
        } catch (_: Exception) {
            ""
        }
    }

    private fun weatherValuesUIToDomain(weatherValues: WeatherValuesUI): WeatherValues {
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

    private fun weatherValuesDomainToUI(weatherValues: WeatherValues): WeatherValuesUI {
        return WeatherValuesUI(
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
