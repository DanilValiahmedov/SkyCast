package com.valimade.skycast.weather.ui

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valimade.skycast.R
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.weather.ui.components.CardWeather
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val viewModel: WeatherViewModel = koinViewModel()
    val weatherState by viewModel.weatherState.collectAsState()


    // Лаунчер для запроса разрешения
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.getPermission(isGranted)
    }

    // Запрос разрешения на геолокацию
    LaunchedEffect(Unit) {
        if (!weatherState.isPermission) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }


    // API-запросы при первом заходе
    LaunchedEffect(weatherState.isFirstLaunch) {
        if (weatherState.location != null && weatherState.isFirstLaunch) {
            viewModel.startApp()
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
                onUpdate = {
                    viewModel.replayApp()
                }
            )
        }
    }
}