package com.valimade.skycast.weather.di

import com.valimade.skycast.core.utils.ApiKeys
import com.valimade.skycast.di.nameWeather
import com.valimade.skycast.di.provideHttpClient
import com.valimade.skycast.weather.data.mapper.WeatherDataMapper
import com.valimade.skycast.weather.data.repository.WeatherRepositoryImpl
import com.valimade.skycast.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {

    single<WeatherDataMapper> {
        WeatherDataMapper
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            httpClient = provideHttpClient(nameWeather),
            mapper = get(),
            apikey = ApiKeys.tomorrowApiKey,
        )
    }

}