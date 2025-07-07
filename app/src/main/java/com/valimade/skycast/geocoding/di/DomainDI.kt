package com.valimade.skycast.geocoding.di

import com.valimade.skycast.geocoding.domain.usecase.ReverseGeocodingUseCase
import org.koin.dsl.module

val domainModule = module {

    single<ReverseGeocodingUseCase> {
        ReverseGeocodingUseCase(
            repository = get(),
        )
    }

}