package com.valimade.skycast.weather.di

import org.koin.dsl.module

val weatherModule = module {
    includes(domainModule, dataModule)
}