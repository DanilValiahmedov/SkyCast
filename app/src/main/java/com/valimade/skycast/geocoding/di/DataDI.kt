package com.valimade.skycast.geocoding.di

import com.valimade.skycast.core.utils.ApiKeys
import com.valimade.skycast.di.nameGeocoding
import com.valimade.skycast.geocoding.data.mapper.GeocodingDataMapper
import com.valimade.skycast.geocoding.data.repository.GeocodingRepositoryImpl
import com.valimade.skycast.geocoding.domain.repository.GeocodingRepository
import org.koin.dsl.module

val dataModule = module {

    single<GeocodingDataMapper> {
        GeocodingDataMapper
    }

    single<GeocodingRepository> {
        GeocodingRepositoryImpl(
            httpClient = get(nameGeocoding),
            mapper = get(),
            apiKey = ApiKeys.geoapifyApiKey,
        )
    }

}