package com.valimade.skycast.weather.ui.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.ui.theme.secondaryColor
import com.valimade.skycast.weather.ui.model.WeatherScreenState

@Composable
fun CardWeather(
    weatherState: WeatherScreenState,
    nameCard: String,
    onUpdate: () -> Unit,
) {
    var isCurrentWeather by remember { mutableStateOf(false) }
    var isForecastWeather by remember { mutableStateOf(false) }

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

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = weatherState.locationGeocoding,
            color = secondaryColor,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeatherInfoGrid(listItem = weatherState.baseWeatherRealtime)

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
                text = "Обновлено: ${weatherState.dateAndTime}",
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
            weatherState.additionalWeatherRealtime.map{
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
                items(weatherState.forecastList) { forecast ->

                    Column(
                        modifier = Modifier.width(145.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        forecast.forecast.forEach {
                            ForecastCard(
                                item = it
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                    }
                    Spacer(modifier = Modifier.width(8.dp))

                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}