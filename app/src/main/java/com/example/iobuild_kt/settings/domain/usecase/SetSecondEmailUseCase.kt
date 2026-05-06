package com.example.iobuild_kt.settings.domain.usecase

import com.example.iobuild_kt.settings.domain.repository.SettingsRepository

class SetSecondEmailUseCase(private val repository: SettingsRepository) {
    suspend operator fun invoke(userId: Int, email: String) = repository.setSecondEmail(userId, email)
}
