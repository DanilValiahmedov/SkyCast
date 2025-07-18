package com.valimade.skycast.permission.di

import com.valimade.skycast.permission.data.repository.PermissionRepositoryImpl
import com.valimade.skycast.permission.domain.repository.PermissionRepository
import com.valimade.skycast.permission.domain.usecase.GetLocationPermissionUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val permissionModule = module {

    //data-слой
    single<PermissionRepository> {
        PermissionRepositoryImpl(
            context = androidContext()
        )
    }

    //domain-слой
    singleOf(::GetLocationPermissionUseCase)

}