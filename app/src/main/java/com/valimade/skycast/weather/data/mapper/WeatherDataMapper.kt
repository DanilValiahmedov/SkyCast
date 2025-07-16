package com.valimade.skycast.weather.data.mapper

import com.valimade.skycast.weather.data.model.WeatherInformData
import com.valimade.skycast.weather.data.model.WeatherValuesData
import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.domain.model.WeatherValues

object WeatherDataMapper {

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
