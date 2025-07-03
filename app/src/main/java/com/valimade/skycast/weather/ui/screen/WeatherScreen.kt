package com.valimade.skycast.weather.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.skycast.R
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.ui.theme.secondaryColor
import com.valimade.skycast.weather.ui.model.DataInfoItem
import com.valimade.skycast.weather.ui.screenelement.WeatherInfoGrid
import com.valimade.skycast.weather.ui.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen() {

    val viewModel: WeatherViewModel = koinViewModel()
    val weather by viewModel.weather.collectAsState()

    val weatherList = remember { mutableStateListOf(
        DataInfoItem(
            icon = R.drawable.ic_temperature,
            name = "Температура",
            value = weather.weather.data.values.temperature.toString(),
            units = "°C",
        ),
        DataInfoItem(
            icon = R.drawable.ic_pressure,
            name = "Давление",
            value = weather.weather.data.values.pressureSurfaceLevel.toString(),
            units = "гПа",
        ),
        DataInfoItem(
            icon = R.drawable.ic_humidity,
            name = "Влажность",
            value = weather.weather.data.values.humidity.toString(),
            units = "%",
        ),
        DataInfoItem(
            icon = R.drawable.ic_wind,
            name = "Скорость ветра",
            value = weather.weather.data.values.windSpeed.toString(),
            units = "м/с",
        ),
    ) }

    LaunchedEffect(Unit) {
        viewModel.getRealtimeWeather(lat = 59.938784, lon = 30.314997)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Иконка",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(32.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = primaryColor, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                text = "Ваше текущее местоположение",
                color = secondaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            WeatherInfoGrid(listItem = weatherList)

        }

    }
}