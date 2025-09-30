package com.zulfikar.suweleh.settleinjapan

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.zulfikar.suweleh.settleinjapan.di.commonModule
import com.zulfikar.suweleh.settleinjapan.di.networkModule
import kotlinx.browser.document
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(commonModule)
        modules(networkModule)
    }
    ComposeViewport(document.body!!) {
        App()
    }
}