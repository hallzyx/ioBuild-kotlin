package com.example.iobuild_kt.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iobuild_kt.core.data.TokenManager
import com.example.iobuild_kt.settings.domain.repository.PasswordChange
import com.example.iobuild_kt.settings.domain.usecase.ChangePasswordUseCase
import com.example.iobuild_kt.settings.domain.usecase.SetSecondEmailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class SettingsUiState(
    val passwordChanged: Boolean? = null,
    val emailChanged: Boolean? = null,
    val error: String? = null,
    val biometricEnabled: Boolean = false
)

class SettingsViewModel(
    private val changePassword: ChangePasswordUseCase,
    private val setSecondEmail: SetSecondEmailUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsUiState())
    val state: StateFlow<SettingsUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                biometricEnabled = tokenManager.biometricEnabled.first()
            )
        }
    }

    fun toggleBiometric(enabled: Boolean) {
        viewModelScope.launch {
            tokenManager.setBiometricEnabled(enabled)
            _state.value = _state.value.copy(biometricEnabled = enabled)
        }
    }

    fun changePassword(userId: Int, current: String, newPassword: String) {
        viewModelScope.launch {
            try {
                changePassword(userId, PasswordChange(current, newPassword))
                _state.value = SettingsUiState(passwordChanged = true)
            } catch (e: Exception) {
                _state.value = SettingsUiState(error = e.message)
            }
        }
    }

    fun setSecondEmail(userId: Int, email: String) {
        viewModelScope.launch {
            try {
                setSecondEmail(userId, email)
                _state.value = SettingsUiState(emailChanged = true)
            } catch (e: Exception) {
                _state.value = SettingsUiState(error = e.message)
            }
        }
    }

    fun resetState() { _state.value = SettingsUiState() }
}
