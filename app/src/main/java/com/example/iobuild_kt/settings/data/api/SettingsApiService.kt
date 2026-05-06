package com.example.iobuild_kt.settings.data.api

import com.example.iobuild_kt.settings.data.dto.PasswordChangeResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class ChangePasswordRequest(val currentPassword: String, val newPassword: String)
data class SecondEmailRequest(val secondEmail: String)

interface SettingsApiService {
    @PUT("users/{id}/password")
    suspend fun changePassword(@Path("id") userId: Int, @Body request: ChangePasswordRequest): PasswordChangeResponse

    @POST("profiles/second-email")
    suspend fun setSecondEmail(@Query("userId") userId: Int, @Body request: SecondEmailRequest)
}
