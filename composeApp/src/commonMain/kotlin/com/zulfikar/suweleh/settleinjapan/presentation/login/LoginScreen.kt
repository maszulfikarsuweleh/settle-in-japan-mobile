package com.zulfikar.suweleh.settleinjapan.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zulfikar.suweleh.settleinjapan.di.AppModule

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = AppModule.provideLoginViewModel()) {

    val uiState by loginViewModel.uiState.collectAsState()
    val username by loginViewModel.username.collectAsState()
    val password by loginViewModel.password.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { loginViewModel.onUsernameChange(it) },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { loginViewModel.onPasswordChange(it) },
            label = { Text("Password") },
            singleLine = true,
            // visualTransformation = PasswordVisualTransformation(), // Uncomment for password hiding
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { loginViewModel.login() },
            enabled = uiState !is LoginUiState.Loading, // Disable button when loading
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState is LoginUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Login")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display messages based on UI state
        when (val state = uiState) {
            is LoginUiState.Success -> {
                Text("Login Successful! Token: ${state.loginResponse.token}", color = MaterialTheme.colorScheme.primary)
                // You might want to navigate to another screen here
            }
            is LoginUiState.Error -> {
                Text("Login Failed: ${state.message}", color = MaterialTheme.colorScheme.error)
            }
            else -> { // Idle or other states
                // You can show other messages or keep it empty
            }
        }
    }
}
