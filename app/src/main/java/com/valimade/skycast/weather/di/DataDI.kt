package com.valimade.skycast.weather.di

import com.valimade.skycast.weather.data.mapper.WeatherDataMapper
import com.valimade.skycast.weather.data.repository.WeatherRepositoryImpl
import com.valimade.skycast.weather.domain.repository.WeatherRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<WeatherDataMapper> {
        WeatherDataMapper
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            httpClient = get(named("WEATHER")),
            mapper = get(),
            apikey = "eOS4mo5gviQWawbhCl5LwtPoyhFvYsnE"
        )
    }

}