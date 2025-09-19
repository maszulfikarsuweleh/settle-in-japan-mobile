package com.zulfikar.suweleh.settleinjapan

import android.app.Application
import com.zulfikar.suweleh.settleinjapan.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AndroidSettleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidSettleApplication)
            androidLogger(level = Level.DEBUG)
            modules(commonModule)
        }
    }
}