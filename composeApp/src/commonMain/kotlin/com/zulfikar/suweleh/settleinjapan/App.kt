package com.zulfikar.suweleh.settleinjapan

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.zulfikar.suweleh.settleinjapan.presentation.login.LoginScreen // Added import

@Composable
@Preview
fun App() {
    MaterialTheme {
        LoginScreen() // Replaced original content with LoginScreen
    }
}
