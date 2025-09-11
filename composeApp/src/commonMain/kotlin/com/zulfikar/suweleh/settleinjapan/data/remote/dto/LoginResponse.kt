package com.zulfikar.suweleh.settleinjapan.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String? = null, // Assuming the token can be nullable
    val message: String? = null, // Assuming a message can be part of the response
    // Add other fields from your actual API response if needed
)
