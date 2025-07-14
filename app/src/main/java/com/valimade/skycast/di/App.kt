package com.valimade.skycast.di

import android.app.Application
import com.valimade.skycast.geocoding.di.geocodingModule
import com.valimade.skycast.weather.di.weatherModule
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                httpClientModule,
                weatherModule,
                geocodingModule,
            )
        }
    }
}