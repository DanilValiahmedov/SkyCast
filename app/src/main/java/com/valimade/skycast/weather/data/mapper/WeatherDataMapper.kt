package com.valimade.skycast.weather.data.mapper

import com.valimade.skycast.weather.data.model.WeatherInformData
import com.valimade.skycast.weather.data.model.realtime.WeatherRealtimeData
import com.valimade.skycast.weather.data.model.WeatherValuesData
import com.valimade.skycast.weather.data.model.forecast.WeatherForecastData
import com.valimade.skycast.weather.data.model.forecast.WeatherTimelinesData
import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.model.realtime.WeatherRealtime
import com.valimade.skycast.weather.domain.model.WeatherValues
import com.valimade.skycast.weather.domain.model.forecast.WeatherForecast
import com.valimade.skycast.weather.domain.model.forecast.WeatherTimelines

object WeatherDataMapper {

    fun weatherForecastDataToDomain(weatherForecast: WeatherForecastData): WeatherForecast {
        return WeatherForecast(
            timelines = weatherTimelinesDataToDomain(weatherForecast.timelines),
        )
    }

    fun weatherForecastDomainToData(weatherForecast: WeatherForecast): WeatherForecastData {
        return WeatherForecastData(
            timelines = weatherTimelinesDomainToData(weatherForecast.timelines),
        )
    }

    private fun weatherTimelinesDataToDomain(weatherTimelines: WeatherTimelinesData): WeatherTimelines {
        return WeatherTimelines(
            daily = weatherTimelines.daily.map {
                weatherInformDataToDomain(it)
            },
            hourly = weatherTimelines.hourly.map {
                weatherInformDataToDomain(it)
            },
        )
    }

    private fun weatherTimelinesDomainToData(weatherTimelines: WeatherTimelines): WeatherTimelinesData {
        return WeatherTimelinesData(
            daily = weatherTimelines.daily.map {
                weatherInformDomainToData(it)
            },
            hourly = weatherTimelines.hourly.map {
                weatherInformDomainToData(it)
            },
        )
    }

    fun weatherRealtimeDataToDomain(weatherRealtime: WeatherRealtimeData): WeatherRealtime {
        return WeatherRealtime(
            data = weatherInformDataToDomain(weatherRealtime.data),
        )
    }

    fun weatherRealtimeDomainToData(weatherRealtime: WeatherRealtime): WeatherRealtimeData {
        return WeatherRealtimeData(
            data = weatherInformDomainToData(weatherRealtime.data),
        )
    }

    private fun weatherInformDataToDomain(weatherInform: WeatherInformData): WeatherInform {
        return WeatherInform(
            time = weatherInform.time,
            values = weatherValuesDataToDomain(weatherInform.values),
        )
    }

    private fun weatherInformDomainToData(weatherInform: WeatherInform): WeatherInformData {
        return WeatherInformData(
            time = weatherInform.time,
            values = weatherValuesDomainToData(weatherInform.values),
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
