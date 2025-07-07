package com.valimade.skycast.geocoding.di

import org.koin.dsl.module

val geocodingModule = module {
    includes(domainModule, dataModule)
}