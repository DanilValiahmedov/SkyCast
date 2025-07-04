package com.valimade.skycast.weather.ui.screen

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.valimade.skycast.R
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.weather.ui.screenelement.CardWeather
import com.valimade.skycast.weather.ui.viewmodel.WeatherViewModel
import kotlinx.coroutines.tasks.await
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val viewModel: WeatherViewModel = koinViewModel()
    val weatherState by viewModel.weatherState.collectAsState()

    val locationClient = remember(context) {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var permissionGranted by remember { mutableStateOf(false) }

    // Лаунчер для запроса разрешения
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
    }

    // Запрос разрешения при первом запуске
    LaunchedEffect(Unit) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            permissionGranted = true
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Получение геолокации при наличии разрешения
    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            try {
                val location = locationClient.lastLocation.await()
                location?.let {
                    viewModel.getRealtimeWeather(it.latitude, it.longitude)
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка при получении геолокации", Toast.LENGTH_SHORT).show()
            }
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
        } else if(weatherState.isError) {
            Toast.makeText(context, "Возникла ошибка, попробуйте позже", Toast.LENGTH_SHORT).show()
        } else {

            CardWeather(
                weatherState = weatherState,
                nameCard = "Текущее положение",
            )

        }
    }
}