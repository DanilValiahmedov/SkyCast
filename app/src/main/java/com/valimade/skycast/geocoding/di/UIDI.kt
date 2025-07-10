package com.valimade.skycast.geocoding.di

import com.valimade.skycast.geocoding.ui.mapper.GeocodingUIMapper
import org.koin.dsl.module

val uiModule = module {

    single<GeocodingUIMapper> {
        GeocodingUIMapper
    }

}