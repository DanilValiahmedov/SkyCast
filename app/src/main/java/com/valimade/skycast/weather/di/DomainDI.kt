package com.valimade.skycast.weather.di

import com.valimade.skycast.weather.domain.usecase.RealtimeWeatherUseCase
import org.koin.dsl.module

val domainModule = module {

    single<RealtimeWeatherUseCase> {
        RealtimeWeatherUseCase(
            repository = get(),
        )
    }

}