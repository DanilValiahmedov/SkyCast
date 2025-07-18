package com.valimade.skycast.location.di

import com.valimade.skycast.location.data.repository.LocationRepositoryImpl
import com.valimade.skycast.location.domain.repository.LocationRepository
import com.valimade.skycast.location.domain.usecase.GetLocationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val locationModule = module {

    //data-слой
    single<LocationRepository> {
        LocationRepositoryImpl(
            context = androidContext()
        )
    }

    //domain-слой
    singleOf(::GetLocationUseCase)

}