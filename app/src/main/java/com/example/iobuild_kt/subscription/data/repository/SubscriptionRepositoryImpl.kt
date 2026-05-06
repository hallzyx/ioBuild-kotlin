package com.example.iobuild_kt.subscription.data.repository

import com.example.iobuild_kt.subscription.data.api.PlanApiService
import com.example.iobuild_kt.subscription.data.api.SubscriptionApiService
import com.example.iobuild_kt.subscription.data.dto.toDomain
import com.example.iobuild_kt.subscription.domain.model.Plan
import com.example.iobuild_kt.subscription.domain.model.Subscription
import com.example.iobuild_kt.subscription.domain.repository.SubscriptionRepository

class SubscriptionRepositoryImpl(
    private val planApi: PlanApiService,
    private val subscriptionApi: SubscriptionApiService
) : SubscriptionRepository {

    override suspend fun getPlans(): Result<List<Plan>> = runCatching {
        planApi.getPlans().map { it.toDomain() }
    }

    override suspend fun getSubscription(builderId: Int): Result<Subscription?> = runCatching {
        subscriptionApi.getSubscriptions().firstOrNull { it.builderId == builderId }?.toDomain()
    }
}
