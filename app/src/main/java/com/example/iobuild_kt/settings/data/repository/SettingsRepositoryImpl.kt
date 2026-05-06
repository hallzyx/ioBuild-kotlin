package com.example.iobuild_kt.settings.data.repository

import com.example.iobuild_kt.settings.data.api.ChangePasswordRequest
import com.example.iobuild_kt.settings.data.api.SecondEmailRequest
import com.example.iobuild_kt.settings.data.api.SettingsApiService
import com.example.iobuild_kt.settings.domain.repository.PasswordChange
import com.example.iobuild_kt.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val api: SettingsApiService) : SettingsRepository {

    override suspend fun changePassword(userId: Int, data: PasswordChange): Result<Unit> = runCatching {
        api.changePassword(userId, ChangePasswordRequest(data.currentPassword, data.newPassword))
        Unit
    }

    override suspend fun setSecondEmail(userId: Int, email: String): Result<Unit> = runCatching {
        api.setSecondEmail(userId, SecondEmailRequest(email))
    }
}
