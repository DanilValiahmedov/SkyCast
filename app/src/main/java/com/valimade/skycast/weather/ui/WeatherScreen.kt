package com.valimade.skycast.weather.ui

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.skycast.R
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.ui.theme.secondaryColor
import com.valimade.skycast.weather.ui.components.CardWeather
import com.valimade.skycast.weather.ui.model.WeatherIntent
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(
    onNavigateToAdd: () -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val viewModel: WeatherViewModel = koinViewModel()
    val weatherState by viewModel.weatherState.collectAsState()

    // Лаунчер для запроса разрешения
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.sendIntent(WeatherIntent.Permission(isGranted))
    }

    // Запрос разрешения на геолокацию
    LaunchedEffect(Unit) {
        if (!weatherState.isPermission) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    LaunchedEffect(weatherState.isError) {
        if (weatherState.isError) {
            Toast.makeText(context, weatherState.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Иконка",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(48.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        if(weatherState.isLoading) {
            CircularProgressIndicator(color = primaryColor)
        }  else {
            CardWeather(
                weatherState = weatherState,
                nameCard = "Текущее положение: ",
                isCurrentWeather = weatherState.showCurrentWeather,
                currentWeatherClick = { viewModel.sendIntent(WeatherIntent.CurrentWeather) },
                isForecastWeather = weatherState.showForecastWeather,
                forecastWeatherClick = { viewModel.sendIntent(WeatherIntent.ForecastWeather)  },
                onUpdate = { viewModel.sendIntent(WeatherIntent.Replay) },
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = primaryColor, shape = RoundedCornerShape(20.dp))
                    .padding(16.dp)
                    .clickable{ onNavigateToAdd() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Добавить новое место",
                    color = secondaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}