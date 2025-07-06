package com.valimade.skycast.weather.ui.screenelement

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.skycast.R
import com.valimade.skycast.ui.theme.humidityColor
import com.valimade.skycast.ui.theme.pressureColor
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.ui.theme.secondaryColor
import com.valimade.skycast.ui.theme.temperatureColor
import com.valimade.skycast.ui.theme.windColor
import com.valimade.skycast.weather.ui.model.item.DataInfoItem
import com.valimade.skycast.weather.ui.model.state.WeatherScreenState

@Composable
fun CardWeather(
    weatherState: WeatherScreenState,
    nameCard: String,
    onUpdate: () -> Unit,
) {
    var isCurrentWeather by remember { mutableStateOf(false) }
    var isForecastWeather by remember { mutableStateOf(false) }

    val weatherBaseList = listOf(
        DataInfoItem(
            icon = R.drawable.ic_temperature,
            name = "Температура",
            value = weatherState.weatherRealtime.data.values.temperature.toString(),
            units = "°C",
        ),
        DataInfoItem(
            icon = R.drawable.ic_pressure,
            name = "Давление",
            value = weatherState.weatherRealtime.data.values.pressureSurfaceLevel.toString(),
            units = "гПа",
        ),
        DataInfoItem(
            icon = R.drawable.ic_humidity,
            name = "Влажность",
            value = weatherState.weatherRealtime.data.values.humidity.toString(),
            units = "%",
        ),
        DataInfoItem(
            icon = R.drawable.ic_wind,
            name = "Скорость ветра",
            value = weatherState.weatherRealtime.data.values.windSpeed.toString(),
            units = "м/с",
        ),
    )

    val weatherOtherList = listOf(
        DataInfoItem(
            name = "Ощуение температуры",
            value = weatherState.weatherRealtime.data.values.temperatureApparent.toString(),
            units = "°C",
        ),
        DataInfoItem(
            name = "Точка росы",
            value = weatherState.weatherRealtime.data.values.dewPoint.toString(),
            units = "°C",
        ),
        DataInfoItem(
            name = "MAX скорость ветра",
            value = weatherState.weatherRealtime.data.values.windGust.toString(),
            units = "м/c",
        ),
        DataInfoItem(
            name = "Интенсив. осадков",
            value = weatherState.weatherRealtime.data.values.rainIntensity.toString(),
            units = "мм/ч",
        ),
        DataInfoItem(
            name = "Интенсив. замерших осадков",
            value = weatherState.weatherRealtime.data.values.freezingRainIntensity.toString(),
            units = "мм/ч",
        ),
        DataInfoItem(
            name = "Интенсив. снегопада",
            value = weatherState.weatherRealtime.data.values.snowIntensity.toString(),
            units = "мм/ч",
        ),
        DataInfoItem(
            name = "Интенсив. мокрого снега",
            value = weatherState.weatherRealtime.data.values.sleetIntensity.toString(),
            units = "мм/ч",
        ),
        DataInfoItem(
            name = "Вероятность выпадения осадков",
            value = weatherState.weatherRealtime.data.values.precipitationProbability.toString(),
            units = "%",
        ),
        DataInfoItem(
            name = "Видимость",
            value = weatherState.weatherRealtime.data.values.visibility.toString(),
            units = "км",
        ),
        DataInfoItem(
            name = "Покрытие облаками",
            value = weatherState.weatherRealtime.data.values.cloudCover.toString(),
            units = "%",
        ),
        DataInfoItem(
            name = "MIN высота до облаков",
            value = weatherState.weatherRealtime.data.values.cloudBase.toString(),
            units = "км",
        ),
        DataInfoItem(
            name = "MAX высота до облаков",
            value = weatherState.weatherRealtime.data.values.cloudCeiling.toString(),
            units = "км",
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = primaryColor, shape = RoundedCornerShape(20.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = nameCard,
            color = secondaryColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeatherInfoGrid(listItem = weatherBaseList)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onUpdate() },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Обновлено: ${weatherState.weatherRealtime.data.date} ${weatherState.weatherRealtime.data.time}",
                color = Color.Black,
                fontSize = 14.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Обновить",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        MoreDetailedButton(
            isPressed = isCurrentWeather,
            textWhenOpen = "Скрыть текущую погоду",
            textWhenClosed = "Подробнее про текущую погоду",
            onClick = {
                isCurrentWeather = !isCurrentWeather
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (isCurrentWeather) {
            weatherOtherList.map{
                FullWeatherItem(item = it)
            }
        }

        MoreDetailedButton(
            isPressed = isForecastWeather,
            textWhenOpen = "Скрыть прогноз погоды",
            textWhenClosed = "Подробнее про прогноз погоды",
            onClick = {
                isForecastWeather = !isForecastWeather
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if(isForecastWeather) {
            LazyRow {
                items(weatherState.weatherForecast.timelines.hourly) { weather ->

                    Column(
                        modifier = Modifier.width(145.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        ForecastCard(
                            item = DataInfoItem(
                                icon = R.drawable.ic_temperature,
                                name = "Температура",
                                value = weather.values.temperature.toString(),
                                units = "°C",
                                date = weather.date,
                                time = weather.time,
                            ),
                            color = temperatureColor,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        ForecastCard(
                            item = DataInfoItem(
                                icon = R.drawable.ic_pressure,
                                name = "Давление",
                                value = weather.values.pressureSurfaceLevel.toString(),
                                units = "гПа",
                                date = weather.date,
                                time = weather.time,
                            ),
                            color = pressureColor,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        ForecastCard(
                            item = DataInfoItem(
                                icon = R.drawable.ic_humidity,
                                name = "Влажность",
                                value = weather.values.humidity.toString(),
                                units = "%",
                                date = weather.date,
                                time = weather.time,
                            ),
                            color = humidityColor,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        ForecastCard(
                            item = DataInfoItem(
                                icon = R.drawable.ic_wind,
                                name = "Скорость ветра",
                                value = weather.values.windSpeed.toString(),
                                units = "м/с",
                                date = weather.date,
                                time = weather.time,
                            ),
                            color = windColor,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                    }
                    Spacer(modifier = Modifier.width(8.dp))

                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}