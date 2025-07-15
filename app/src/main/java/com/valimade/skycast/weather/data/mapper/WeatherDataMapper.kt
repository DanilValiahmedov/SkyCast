package com.valimade.skycast.weather.data.mapper

import com.valimade.skycast.weather.data.model.WeatherInformData
import com.valimade.skycast.weather.data.model.WeatherValuesData
import com.valimade.skycast.weather.data.model.forecast.WeatherForecastData
import com.valimade.skycast.weather.data.model.forecast.WeatherTimelinesData
import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.model.WeatherValues
import com.valimade.skycast.weather.domain.model.forecast.WeatherForecast
import com.valimade.skycast.weather.domain.model.forecast.WeatherTimelines

object WeatherDataMapper {

    fun weatherForecastDataToDomain(weatherForecast: WeatherForecastData): WeatherForecast {
        return WeatherForecast(
            timelines = weatherTimelinesDataToDomain(weatherForecast.timelines),
        )
    }

    private fun weatherTimelinesDataToDomain(weatherTimelines: WeatherTimelinesData): WeatherTimelines {
        return WeatherTimelines(
            hourly = weatherTimelines.hourly.map {
                weatherInformDataToDomain(it)
            },
        )
    }

    fun weatherInformDataToDomain(weatherInform: WeatherInformData): WeatherInform {
        return WeatherInform(
            time = weatherInform.time,
            values = weatherValuesDataToDomain(weatherInform.values),
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

}
