package com.valimade.skycast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.valimade.skycast.di.httpClientModule
import com.valimade.skycast.ui.theme.SkyCastTheme
import com.valimade.skycast.weather.di.weatherModule
import com.valimade.skycast.weather.ui.screen.WeatherScreen
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startKoin {
            modules(
                httpClientModule,
                weatherModule,
            )
        }

        setContent {
            SkyCastTheme {
                WeatherScreen()
            }
        }
    }
}
