package com.zulfikar.suweleh.settleinjapan.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("access_token")
    val token: String? = null, // Assuming the token can be nullable
    val message: String? = null, // Assuming a message can be part of the response
    // Add other fields from your actual API response if needed
)
