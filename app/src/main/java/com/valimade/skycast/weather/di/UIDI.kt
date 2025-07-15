package com.valimade.skycast.weather.di

import com.valimade.skycast.weather.ui.mapper.WeatherUIMapper
import com.valimade.skycast.weather.ui.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    single<WeatherUIMapper> {
        WeatherUIMapper
    }

     viewModel<WeatherViewModel>{
        WeatherViewModel(
            realtimeWeatherUseCase = get(),
            forecastWeatherUseCase = get(),
            reverseGeocodingUseCase = get(),
            weatherMapper = get(),
            geocodingMapper = get(),
        )
    }

}