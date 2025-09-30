package com.zulfikar.suweleh.settleinjapan.di

import org.koin.core.context.startKoin

object KoinIOS {
    fun doInit() {
        startKoin {
            modules(commonModule)
            modules(networkModule)
        }
    }
}