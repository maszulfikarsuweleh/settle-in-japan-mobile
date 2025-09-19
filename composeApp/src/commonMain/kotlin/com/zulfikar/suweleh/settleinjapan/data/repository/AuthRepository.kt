package com.zulfikar.suweleh.settleinjapan.data.repository

import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginRequest
import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginResponse

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> // Using Result for better error handling
    suspend fun greeting(): Result<String> // Using Result for better error handling
}
