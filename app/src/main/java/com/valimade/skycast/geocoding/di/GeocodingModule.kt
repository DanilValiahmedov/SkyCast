package com.valimade.skycast.geocoding.di

import com.valimade.skycast.core.utils.ApiKeys
import com.valimade.skycast.http.NameAPI
import com.valimade.skycast.http.provideHttpClient
import com.valimade.skycast.geocoding.data.mapper.GeocodingDataMapper
import com.valimade.skycast.geocoding.data.repository.GeocodingRepositoryImpl
import com.valimade.skycast.geocoding.domain.repository.GeocodingRepository
import com.valimade.skycast.geocoding.domain.usecase.ForwardGeocodingUseCase
import com.valimade.skycast.geocoding.domain.usecase.ReverseGeocodingUseCase
import com.valimade.skycast.geocoding.ui.mapper.GeocodingUIMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val geocodingModule = module {

    //data-слой
    single { GeocodingDataMapper }

    single<GeocodingRepository> {
        GeocodingRepositoryImpl(
            httpClient = provideHttpClient(NameAPI.GEOCODING),
            mapper = get(),
            apiKey = ApiKeys.geoapifyApiKey,
        )
    }

    //domain-слой
    singleOf(::ReverseGeocodingUseCase)

    singleOf(::ForwardGeocodingUseCase)

    //ui-слой
    single { GeocodingUIMapper }

}