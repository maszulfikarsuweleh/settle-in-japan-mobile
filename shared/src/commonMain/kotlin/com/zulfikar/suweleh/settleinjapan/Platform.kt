package com.zulfikar.suweleh.settleinjapan

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform