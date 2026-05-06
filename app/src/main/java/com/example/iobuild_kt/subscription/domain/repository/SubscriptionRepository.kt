package com.example.iobuild_kt.subscription.domain.repository

import com.example.iobuild_kt.subscription.domain.model.Plan
import com.example.iobuild_kt.subscription.domain.model.Subscription

interface SubscriptionRepository {
    suspend fun getPlans(): Result<List<Plan>>
    suspend fun getSubscription(builderId: Int): Result<Subscription?>
}
