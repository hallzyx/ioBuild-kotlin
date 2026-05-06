package com.example.iobuild_kt.subscription.data.api

import com.example.iobuild_kt.subscription.data.dto.SubscriptionDto
import retrofit2.http.GET

interface SubscriptionApiService {
    @GET("subscriptions")
    suspend fun getSubscriptions(): List<SubscriptionDto>
}
