package com.valimade.skycast.weather.ui.mapper

import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.model.WeatherValues
import com.valimade.skycast.weather.domain.model.forecast.WeatherForecast
import com.valimade.skycast.weather.domain.model.forecast.WeatherTimelines
import com.valimade.skycast.weather.ui.model.forecast.WeatherForecastUI
import com.valimade.skycast.weather.ui.model.forecast.WeatherTimelinesUI
import com.valimade.skycast.weather.ui.model.WeatherInformUI
import com.valimade.skycast.weather.ui.model.WeatherValuesUI
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object WeatherUIMapper {

    fun weatherForecastDomainToUI(weatherForecast: WeatherForecast): WeatherForecastUI {
        return WeatherForecastUI(
            timelines = weatherTimelinesDomainToUI(weatherForecast.timelines),
        )
    }

    private fun weatherTimelinesDomainToUI(weatherTimelines: WeatherTimelines): WeatherTimelinesUI {
        return WeatherTimelinesUI(
            hourly = weatherTimelines.hourly.map {
                weatherInformDomainToUI(it)
            },
        )
    }

    fun weatherInformDomainToUI(weatherInform: WeatherInform): WeatherInformUI {
        return WeatherInformUI(
            date = timeFormat(weatherInform.time)[0],
            time = timeFormat(weatherInform.time)[1],
            values = weatherValuesDomainToUI(weatherInform.values),
        )
    }

    private fun timeFormat(time: String): List<String>{
        return try {
            val instant = Instant.parse(time)
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                .withZone(ZoneId.systemDefault())
            val listTime = formatter.format(instant).split(" ")
            listTime
        } catch (_: Exception) {
            listOf("", "")
        }
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
