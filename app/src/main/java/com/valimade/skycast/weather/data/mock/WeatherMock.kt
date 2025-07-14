package com.valimade.skycast.weather.data.mock

import com.valimade.skycast.weather.data.model.WeatherInformData
import com.valimade.skycast.weather.data.model.WeatherValuesData
import com.valimade.skycast.weather.data.model.forecast.WeatherForecastData
import com.valimade.skycast.weather.data.model.forecast.WeatherTimelinesData
import com.valimade.skycast.weather.data.model.realtime.WeatherRealtimeData

object WeatherMock {

    val responseRealtime = WeatherRealtimeData(
        data = WeatherInformData(
            time = "2025-07-10T07:40:00Z",
            values = WeatherValuesData(
                cloudBase = 0.07,
                cloudCeiling = 0.07,
                cloudCover = 100,
                dewPoint = 0.88,
                freezingRainIntensity = 0,
                humidity = 96,
                precipitationProbability = 0,
                pressureSurfaceLevel = 984.57,
                rainIntensity = 0,
                sleetIntensity = 0,
                snowIntensity = 0,
                temperature = 1.88,
                temperatureApparent = -0.69,
                uvHealthConcern = 0,
                uvIndex = 0,
                visibility = 9.9,
                weatherCode = 1001,
                windDirection = 10,
                windGust = 3.38,
                windSpeed = 2.38,
            )
        )
    )

    val responseForecast = WeatherForecastData(
        timelines = WeatherTimelinesData(
            hourly = listOf(
                WeatherInformData(
                    time = "2025-07-10T08:00:00Z",
                    values = WeatherValuesData(
                        cloudBase = 0.07,
                        cloudCeiling = 0.07,
                        cloudCover = 100,
                        dewPoint = 0.88,
                        freezingRainIntensity = 0,
                        humidity = 96,
                        precipitationProbability = 0,
                        pressureSurfaceLevel = 984.57,
                        rainIntensity = 0,
                        sleetIntensity = 0,
                        snowIntensity = 0,
                        temperature = 1.88,
                        temperatureApparent = -0.69,
                        uvHealthConcern = 0,
                        uvIndex = 0,
                        visibility = 9.9,
                        weatherCode = 1001,
                        windDirection = 10,
                        windGust = 3.38,
                        windSpeed = 2.38,
                    )
                ),
                WeatherInformData(
                    time = "2025-07-10T09:00:00Z",
                    values = WeatherValuesData(
                        cloudBase = 0.07,
                        cloudCeiling = 0.07,
                        cloudCover = 100,
                        dewPoint = 0.88,
                        freezingRainIntensity = 0,
                        humidity = 96,
                        precipitationProbability = 0,
                        pressureSurfaceLevel = 984.57,
                        rainIntensity = 0,
                        sleetIntensity = 0,
                        snowIntensity = 0,
                        temperature = 1.88,
                        temperatureApparent = -0.69,
                        uvHealthConcern = 0,
                        uvIndex = 0,
                        visibility = 9.9,
                        weatherCode = 1001,
                        windDirection = 10,
                        windGust = 3.38,
                        windSpeed = 2.38,
                    )
                ),
                WeatherInformData(
                    time = "2025-07-10T10:00:00Z",
                    values = WeatherValuesData(
                        cloudBase = 0.07,
                        cloudCeiling = 0.07,
                        cloudCover = 100,
                        dewPoint = 0.88,
                        freezingRainIntensity = 0,
                        humidity = 96,
                        precipitationProbability = 0,
                        pressureSurfaceLevel = 984.57,
                        rainIntensity = 0,
                        sleetIntensity = 0,
                        snowIntensity = 0,
                        temperature = 1.88,
                        temperatureApparent = -0.69,
                        uvHealthConcern = 0,
                        uvIndex = 0,
                        visibility = 9.9,
                        weatherCode = 1001,
                        windDirection = 10,
                        windGust = 3.38,
                        windSpeed = 2.38,
                    )
                ),
                WeatherInformData(
                    time = "2025-07-10T11:00:00Z",
                    values = WeatherValuesData(
                        cloudBase = 0.07,
                        cloudCeiling = 0.07,
                        cloudCover = 100,
                        dewPoint = 0.88,
                        freezingRainIntensity = 0,
                        humidity = 96,
                        precipitationProbability = 0,
                        pressureSurfaceLevel = 984.57,
                        rainIntensity = 0,
                        sleetIntensity = 0,
                        snowIntensity = 0,
                        temperature = 1.88,
                        temperatureApparent = -0.69,
                        uvHealthConcern = 0,
                        uvIndex = 0,
                        visibility = 9.9,
                        weatherCode = 1001,
                        windDirection = 10,
                        windGust = 3.38,
                        windSpeed = 2.38,
                    )
                ),
                WeatherInformData(
                    time = "2025-07-10T12:00:00Z",
                    values = WeatherValuesData(
                        cloudBase = 0.07,
                        cloudCeiling = 0.07,
                        cloudCover = 100,
                        dewPoint = 0.88,
                        freezingRainIntensity = 0,
                        humidity = 96,
                        precipitationProbability = 0,
                        pressureSurfaceLevel = 984.57,
                        rainIntensity = 0,
                        sleetIntensity = 0,
                        snowIntensity = 0,
                        temperature = 1.88,
                        temperatureApparent = -0.69,
                        uvHealthConcern = 0,
                        uvIndex = 0,
                        visibility = 9.9,
                        weatherCode = 1001,
                        windDirection = 10,
                        windGust = 3.38,
                        windSpeed = 2.38,
                    )
                ),
            )
        )
    )
}