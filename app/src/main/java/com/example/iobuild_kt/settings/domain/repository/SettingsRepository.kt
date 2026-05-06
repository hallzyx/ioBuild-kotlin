package com.example.iobuild_kt.settings.domain.repository

data class PasswordChange(val currentPassword: String, val newPassword: String)

interface SettingsRepository {
    suspend fun changePassword(userId: Int, data: PasswordChange): Result<Unit>
    suspend fun setSecondEmail(userId: Int, email: String): Result<Unit>
}
