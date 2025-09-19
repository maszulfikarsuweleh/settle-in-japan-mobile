package com.zulfikar.suweleh.settleinjapan.domain.usecase

import com.zulfikar.suweleh.settleinjapan.data.repository.AuthRepository

class GreetingUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Result<String> {
        return authRepository.greeting()
    }
}