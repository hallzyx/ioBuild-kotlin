package com.example.iobuild_kt.settings.domain.usecase

import com.example.iobuild_kt.settings.domain.repository.PasswordChange
import com.example.iobuild_kt.settings.domain.repository.SettingsRepository

class ChangePasswordUseCase(private val repository: SettingsRepository) {
    suspend operator fun invoke(userId: Int, data: PasswordChange) = repository.changePassword(userId, data)
}
