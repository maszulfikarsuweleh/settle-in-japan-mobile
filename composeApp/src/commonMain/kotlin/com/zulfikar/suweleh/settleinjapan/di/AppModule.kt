package com.zulfikar.suweleh.settleinjapan.di

import com.zulfikar.suweleh.settleinjapan.data.remote.AuthApiService
import com.zulfikar.suweleh.settleinjapan.data.remote.AuthApiServiceImpl
import com.zulfikar.suweleh.settleinjapan.data.repository.AuthRepository
import com.zulfikar.suweleh.settleinjapan.data.repository.AuthRepositoryImpl
import com.zulfikar.suweleh.settleinjapan.domain.usecase.LoginUseCase
import com.zulfikar.suweleh.settleinjapan.presentation.login.LoginViewModel

// A simple object to provide dependencies. 
// For a larger app, consider a DI framework like Koin or Hilt (for Android-specific parts).
object AppModule {

    // Data Layer
    private val authApiService: AuthApiService by lazy {
        AuthApiServiceImpl()
    }

    private val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authApiService = authApiService)
    }

    // Domain Layer
    private val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(authRepository = authRepository)
    }

    // Presentation Layer
    // ViewModel instances are typically created by a factory or a DI framework in the UI layer
    // Here we provide a function to create it, which can be used by a ViewModel factory if needed.
    fun provideLoginViewModel(): LoginViewModel {
        return LoginViewModel(loginUseCase = loginUseCase)
    }
}
