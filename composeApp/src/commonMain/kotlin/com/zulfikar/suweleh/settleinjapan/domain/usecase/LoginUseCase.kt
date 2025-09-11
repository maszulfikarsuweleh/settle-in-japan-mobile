package com.zulfikar.suweleh.settleinjapan.domain.usecase

import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginRequest
import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginResponse
// Corrected import for AuthRepository
import com.zulfikar.suweleh.settleinjapan.data.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(loginRequest: LoginRequest): Result<LoginResponse> {
        // Basic validation (can be more sophisticated)
        if (loginRequest.username.isBlank() || loginRequest.password.isBlank()) {
            return Result.failure(IllegalArgumentException("Username and password cannot be empty"))
        }
        return authRepository.login(loginRequest)
    }
}
