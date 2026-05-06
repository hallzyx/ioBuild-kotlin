package com.example.iobuild_kt.subscription.domain.usecase

import com.example.iobuild_kt.subscription.domain.repository.SubscriptionRepository

class GetSubscriptionUseCase(private val repository: SubscriptionRepository) {
    suspend operator fun invoke(builderId: Int) = repository.getSubscription(builderId)
}
