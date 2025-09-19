package com.zulfikar.suweleh.settleinjapan.di

import com.zulfikar.suweleh.settleinjapan.data.remote.AuthApiService
import com.zulfikar.suweleh.settleinjapan.data.remote.AuthApiServiceImpl
import com.zulfikar.suweleh.settleinjapan.data.repository.AuthRepository
import com.zulfikar.suweleh.settleinjapan.data.repository.AuthRepositoryImpl
import com.zulfikar.suweleh.settleinjapan.domain.usecase.GreetingUseCase
import com.zulfikar.suweleh.settleinjapan.domain.usecase.LoginUseCase
import com.zulfikar.suweleh.settleinjapan.presentation.login.LoginViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

// Koin module for your application's dependencies
val commonModule = module {
    // Data Layer
    singleOf(::AuthApiServiceImpl) bind AuthApiService::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class

    // Domain Layer
    singleOf(::LoginUseCase)
    singleOf(::GreetingUseCase)

    // Presentation Layer
    // For ViewModels, it's common to use viewModelOf if using Koin's ViewModel integration,
    // but for simple KMP ViewModels, factoryOf or singleOf can also work.
    // Using factoryOf for ViewModels is often preferred if they have a shorter lifecycle
    // or need to be created fresh each time they are requested by a Composable.
    // For this example, as LoginViewModel is a simple class, singleOf might be fine too,
    // or koin-compose's koinViewModel() will handle its lifecycle.
    // Let's use singleOf for now, which means one instance of LoginViewModel will be shared.
    // If you need a new instance per screen, use factoryOf(::LoginViewModel)
    factoryOf(::LoginViewModel)
}
