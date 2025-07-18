package com.valimade.skycast.weather.ui.mapper

import com.valimade.skycast.R
import com.valimade.skycast.ui.theme.humidityColor
import com.valimade.skycast.ui.theme.pressureColor
import com.valimade.skycast.ui.theme.temperatureColor
import com.valimade.skycast.ui.theme.windColor
import com.valimade.skycast.weather.domain.model.WeatherInform
import com.valimade.skycast.weather.ui.model.forecat.ForecastList
import com.valimade.skycast.weather.ui.model.forecat.ForecastWeather
import com.valimade.skycast.weather.ui.model.mapper.TimeResponse
import com.valimade.skycast.weather.ui.model.realtime.AdditionalWeather
import com.valimade.skycast.weather.ui.model.realtime.BaseWeather
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object WeatherUIMapper {

    fun baseWeatherDomainToUI(weatherInform: WeatherInform): List<BaseWeather> {
        return listOf(
            BaseWeather(
                icon = R.drawable.ic_temperature,
                name = "Температура",
                value = weatherInform.values.temperature.toString(),
                units = "°C",
            ),
            BaseWeather(
                icon = R.drawable.ic_pressure,
                name = "Давление",
                value = weatherInform.values.pressureSurfaceLevel.toString(),
                units = "гПа",
            ),
            BaseWeather(
                icon = R.drawable.ic_humidity,
                name = "Влажность",
                value = weatherInform.values.humidity.toString(),
                units = "%",
            ),
            BaseWeather(
                icon = R.drawable.ic_wind,
                name = "Скорость ветра",
                value = weatherInform.values.windSpeed.toString(),
                units = "м/с",
            ),
        )
    }

    fun additionalWeatherDomainToUI(weatherInform: WeatherInform): List<AdditionalWeather> {
        val allList = listOf(
            AdditionalWeather(
                name = "Ощущение температуры",
                value = weatherInform.values.temperatureApparent.toString(),
                units = "°C",
            ),
            AdditionalWeather(
                name = "Точка росы",
                value = weatherInform.values.dewPoint.toString(),
                units = "°C",
            ),
            AdditionalWeather(
                name = "MAX скорость ветра",
                value = weatherInform.values.windGust.toString(),
                units = "м/c",
            ),
            AdditionalWeather(
                name = "Интенсив. замерших осадков",
                value = weatherInform.values.freezingRainIntensity.toString(),
                units = "мм/ч",
            ),
            AdditionalWeather(
                name = "Интенсив. снегопада",
                value = weatherInform.values.snowIntensity.toString(),
                units = "мм/ч",
            ),
            AdditionalWeather(
                name = "Интенсив. мокрого снега",
                value = weatherInform.values.sleetIntensity.toString(),
                units = "мм/ч",
            ),
            AdditionalWeather(
                name = "Вероятность выпадения осадков",
                value = weatherInform.values.precipitationProbability.toString(),
                units = "%",
            ),
            AdditionalWeather(
                name = "Видимость",
                value = weatherInform.values.visibility.toString(),
                units = "км",
            ),
            AdditionalWeather(
                name = "Покрытие облаками",
                value = weatherInform.values.cloudCover.toString(),
                units = "%",
            ),
            AdditionalWeather(
                name = "MIN высота до облаков",
                value = weatherInform.values.cloudBase.toString(),
                units = "км",
            ),
            AdditionalWeather(
                name = "MAX высота до облаков",
                value = weatherInform.values.cloudCeiling.toString(),
                units = "км",
            ),
        )

        return allList.filter{
            !(it.value == "0.0" || it.value == "0" || it.value == "null")
        }
    }

    fun dateAndTimeDomainToUI(weatherInform: WeatherInform): String {
        return timeFormat(weatherInform.time, TimeResponse.DATEANDTIME)
    }

    fun forecastListDomainToUI(weatherInform: WeatherInform): ForecastList {
        return ForecastList(
            forecast = listOf(
                ForecastWeather(
                    icon = R.drawable.ic_temperature,
                    name = "Температура",
                    value = weatherInform.values.temperature.toString(),
                    units = "°C",
                    date = timeFormat(weatherInform.time, TimeResponse.DATE),
                    time = timeFormat(weatherInform.time, TimeResponse.TIME),
                    color = temperatureColor,
                ),
                ForecastWeather(
                    icon = R.drawable.ic_pressure,
                    name = "Давление",
                    value = weatherInform.values.pressureSurfaceLevel.toString(),
                    units = "гПа",
                    date = timeFormat(weatherInform.time, TimeResponse.DATE),
                    time = timeFormat(weatherInform.time, TimeResponse.TIME),
                    color = pressureColor,
                ),
                ForecastWeather(
                    icon = R.drawable.ic_humidity,
                    name = "Влажность",
                    value = weatherInform.values.humidity.toString(),
                    units = "%",
                    date = timeFormat(weatherInform.time, TimeResponse.DATE),
                    time = timeFormat(weatherInform.time, TimeResponse.TIME),
                    color = humidityColor,
                ),
                ForecastWeather(
                    icon = R.drawable.ic_wind,
                    name = "Скорость ветра",
                    value = weatherInform.values.windSpeed.toString(),
                    units = "м/с",
                    date = timeFormat(weatherInform.time, TimeResponse.DATE),
                    time = timeFormat(weatherInform.time, TimeResponse.TIME),
                    color = windColor,
                ),
            )
        )
    }

    private fun timeFormat(time: String, response: TimeResponse): String {
        if (time == "") return ""

        val instant = Instant.parse(time)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            .withZone(ZoneId.systemDefault())
        val listTime = formatter.format(instant).split(" ")

        return when (response) {
            TimeResponse.DATE -> listTime[0]
            TimeResponse.TIME -> listTime[1]
            TimeResponse.DATEANDTIME -> "${listTime[0]} ${listTime[1]}"
        }
    }

}
