package com.zulfikar.suweleh.settleinjapan.data.repository

import com.zulfikar.suweleh.settleinjapan.data.remote.AuthApiService
import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginRequest
import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginResponse

class AuthRepositoryImpl(
    private val authApiService: AuthApiService
) : AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = authApiService.login(loginRequest)
            // You might want to add more specific error checking based on response content here
            // For example, if the API indicates an error via a non-null message but a null token.
            if (response.token != null) {
                Result.success(response)
            } else {
                Result.failure(Exception(response.message ?: "Login failed: No token received"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun greeting(): Result<String> = runCatching {
        return try {
            Result.success(authApiService.testKtor())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
