package com.valimade.skycast.weather.ui.screenelement

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.ui.theme.secondaryColor
import com.valimade.skycast.weather.ui.model.item.DataInfoItem
import com.valimade.skycast.weather.ui.model.state.WeatherScreenState

@Composable
fun CardWeather(
    weatherState: WeatherScreenState,
    nameCard: String,
    onUpdate: () -> Unit,
) {
    var isCardOpen by remember { mutableStateOf(false) }

    val weatherList = remember(weatherState) { mutableStateListOf(
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
    ) }

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

        WeatherInfoGrid(listItem = weatherList)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ){
                    onUpdate()
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Обновлено: ${weatherState.weatherRealtime.data.time}",
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ){
                    isCardOpen = !isCardOpen
                },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if(isCardOpen) "Скрыть"
                    else "Подробнее",
                color = Color.Black,
                fontSize = 14.sp,
            )

            Icon(
                imageVector = if(isCardOpen) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = "Подробнее",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if(isCardOpen) {
            FullWeatherItem(
                name = "Ощуение температуры",
                value = weatherState.weatherRealtime.data.values.temperatureApparent.toString(),
                units = "°C",
            )

            FullWeatherItem(
                name = "Точка росы",
                value = weatherState.weatherRealtime.data.values.dewPoint.toString(),
                units = "°C",
            )

            FullWeatherItem(
                name = "MAX скорость ветра",
                value = weatherState.weatherRealtime.data.values.windGust.toString(),
                units = "м/c",
            )

            FullWeatherItem(
                name = "Интенсивность осадков",
                value = weatherState.weatherRealtime.data.values.rainIntensity.toString(),
                units = "мм/ч",
            )

            FullWeatherItem(
                name = "Интенсивность замерших осадков",
                value = weatherState.weatherRealtime.data.values.freezingRainIntensity.toString(),
                units = "мм/ч",
            )

            FullWeatherItem(
                name = "Интенсивность снегопада",
                value = weatherState.weatherRealtime.data.values.snowIntensity.toString(),
                units = "мм/ч",
            )

            FullWeatherItem(
                name = "Интенсивность мокрого снега",
                value = weatherState.weatherRealtime.data.values.sleetIntensity.toString(),
                units = "мм/ч",
            )

            FullWeatherItem(
                name = "Вероятность выпадения осадков",
                value = weatherState.weatherRealtime.data.values.precipitationProbability.toString(),
                units = "%",
            )

            FullWeatherItem(
                name = "Видимость",
                value = weatherState.weatherRealtime.data.values.visibility.toString(),
                units = "км",
            )

            FullWeatherItem(
                name = "Покрытие облаками",
                value = weatherState.weatherRealtime.data.values.cloudCover.toString(),
                units = "%",
            )

            FullWeatherItem(
                name = "MIN высота до облаков",
                value = weatherState.weatherRealtime.data.values.cloudBase.toString(),
                units = "км",
            )

            FullWeatherItem(
                name = "MAX высота до облаков",
                value = weatherState.weatherRealtime.data.values.cloudCeiling.toString(),
                units = "км",
            )

            FullWeatherItem(
                name = "Ультрафиолетовый индекс",
                value = weatherState.weatherRealtime.data.values.uvIndex.toString(),
                units = "из 11",
            )

            FullWeatherItem(
                name = "Влияние УФ.индекса",
                value = weatherState.weatherRealtime.data.values.uvHealthConcern.toString(),
                units = "из 11",
            )
        }

    }
}