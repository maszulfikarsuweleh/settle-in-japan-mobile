package com.zulfikar.suweleh.settleinjapan.data.remote

import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginRequest
import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface AuthApiService {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}

class AuthApiServiceImpl : AuthApiService {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true // Important for API evolution
            })
        }
        // You might want to configure other client features here, like logging, default headers, etc.
    }

    private val baseUrl = "https://settle-in-japan.onrender.com"

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return try {
            client.post("$baseUrl/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(loginRequest)
            }.body<LoginResponse>()
        } catch (e: Exception) {
            // Basic error handling, consider a more robust error handling strategy for production
            // e.g., mapping to a custom error class or sealed interface
            // Ensure LoginResponse has a constructor that matches this usage if you customize it
            LoginResponse(message = "Login failed: ${e.message}")
        }
    }
}
