package com.example.iobuild_kt.subscription.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iobuild_kt.subscription.domain.model.Plan
import com.example.iobuild_kt.subscription.domain.model.Subscription
import com.example.iobuild_kt.subscription.domain.usecase.GetPlansUseCase
import com.example.iobuild_kt.subscription.domain.usecase.GetSubscriptionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SubscriptionUiState(
    val isLoading: Boolean = true,
    val plans: List<Plan> = emptyList(),
    val subscription: Subscription? = null,
    val error: String? = null
)

class SubscriptionViewModel(
    private val getPlans: GetPlansUseCase,
    private val getSubscription: GetSubscriptionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SubscriptionUiState())
    val state: StateFlow<SubscriptionUiState> = _state.asStateFlow()

    init { loadData() }

    fun loadData(builderId: Int = 1) {
        viewModelScope.launch {
            _state.value = SubscriptionUiState(isLoading = true)
            val plansResult = getPlans()
            val subResult = getSubscription(builderId)
            _state.value = SubscriptionUiState(
                isLoading = false,
                plans = plansResult.getOrDefault(emptyList()),
                subscription = subResult.getOrDefault(null),
                error = if (plansResult.isFailure) plansResult.exceptionOrNull()?.message else null
            )
        }
    }
}
