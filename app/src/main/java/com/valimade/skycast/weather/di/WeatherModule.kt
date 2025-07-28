package com.valimade.skycast.weather.di

import com.valimade.skycast.core.utils.ApiKeys
import com.valimade.skycast.http.NameAPI
import com.valimade.skycast.http.provideHttpClient
import com.valimade.skycast.weather.data.mapper.WeatherDataMapper
import com.valimade.skycast.weather.data.repository.WeatherRepositoryImpl
import com.valimade.skycast.weather.domain.repository.WeatherRepository
import com.valimade.skycast.weather.domain.usecase.ForecastWeatherUseCase
import com.valimade.skycast.weather.domain.usecase.RealtimeWeatherUseCase
import com.valimade.skycast.weather.ui.viewmodel.WeatherViewModel
import com.valimade.skycast.weather.ui.mapper.WeatherUIMapper
import com.valimade.skycast.weather.ui.viewmodel.AddViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {

    //data-слой
    single { WeatherDataMapper }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            httpClient = provideHttpClient(NameAPI.WEATHER),
            mapper = get(),
            apikey = ApiKeys.tomorrowApiKey,
        )
    }

    //domain-слой
    singleOf(::RealtimeWeatherUseCase)

    singleOf(::ForecastWeatherUseCase)

    //ui-слой
    single { WeatherUIMapper }

    viewModel<WeatherViewModel>{
        WeatherViewModel(
            realtimeWeatherUseCase = get(),
            forecastWeatherUseCase = get(),
            reverseGeocodingUseCase = get(),
            weatherMapper = get(),
            geocodingMapper = get(),
            getLocationUseCase = get(),
        )
    }

    viewModel<AddViewModel>{
        AddViewModel(
            forwardGeocodingUseCase = get(),
            geocodingMapper = get(),
        )
    }


}