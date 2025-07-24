package com.valimade.skycast

import android.app.Application
import com.valimade.skycast.geocoding.di.geocodingModule
import com.valimade.skycast.location.di.locationModule
import com.valimade.skycast.permission.di.permissionModule
import com.valimade.skycast.weather.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                weatherModule,
                geocodingModule,
                locationModule,
                permissionModule,
            )
        }
    }
}