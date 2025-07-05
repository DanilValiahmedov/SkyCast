package com.valimade.skycast.weather.ui.mapper

import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.model.realtime.WeatherRealtime
import com.valimade.skycast.weather.domain.model.WeatherValues
import com.valimade.skycast.weather.domain.model.forecast.WeatherForecast
import com.valimade.skycast.weather.domain.model.forecast.WeatherTimelines
import com.valimade.skycast.weather.ui.model.forecast.WeatherForecastUI
import com.valimade.skycast.weather.ui.model.forecast.WeatherTimelinesUI
import com.valimade.skycast.weather.ui.model.realtime.WeatherRealtimeUI
import com.valimade.skycast.weather.ui.model.WeatherInformUI
import com.valimade.skycast.weather.ui.model.WeatherValuesUI
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object WeatherUIMapper {

    fun weatherForecastUIToDomain(weatherForecast: WeatherForecastUI): WeatherForecast {
        return WeatherForecast(
            timelines = weatherTimelinesUIToDomain(weatherForecast.timelines),
        )
    }

    fun weatherForecastDomainToUI(weatherForecast: WeatherForecast): WeatherForecastUI {
        return WeatherForecastUI(
            timelines = weatherTimelinesDomainToUI(weatherForecast.timelines),
        )
    }

    private fun weatherTimelinesUIToDomain(weatherTimelines: WeatherTimelinesUI): WeatherTimelines {
        return WeatherTimelines(
            daily = weatherTimelines.daily.map {
                weatherInformUIToDomain(it)
            },
            hourly = weatherTimelines.hourly.map {
                weatherInformUIToDomain(it)
            },
        )
    }

    private fun weatherTimelinesDomainToUI(weatherTimelines: WeatherTimelines): WeatherTimelinesUI {
        return WeatherTimelinesUI(
            daily = weatherTimelines.daily.map {
                weatherInformDomainToUI(it)
            },
            hourly = weatherTimelines.hourly.map {
                weatherInformDomainToUI(it)
            },
        )
    }

    fun weatherRealtimeUIToDomain(weatherRealtime: WeatherRealtimeUI): WeatherRealtime {
        return WeatherRealtime(
            data = weatherInformUIToDomain(weatherRealtime.data),
        )
    }

    fun weatherRealtimeDomainToUI(weatherRealtime: WeatherRealtime): WeatherRealtimeUI {
        return WeatherRealtimeUI(
            data = weatherInformDomainToUI(weatherRealtime.data),
        )
    }

    private fun weatherInformUIToDomain(weatherInform: WeatherInformUI): WeatherInform {
        return WeatherInform(
            time = weatherInform.time,
            values = weatherValuesUIToDomain(weatherInform.values),
        )
    }

    private fun weatherInformDomainToUI(weatherInform: WeatherInform): WeatherInformUI {
        return WeatherInformUI(
            time = timeFormat(weatherInform.time),
            values = weatherValuesDomainToUI(weatherInform.values),
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
