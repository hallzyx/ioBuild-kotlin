package com.example.iobuild_kt.subscription.data.api

import com.example.iobuild_kt.subscription.data.dto.PlanDto
import retrofit2.http.GET

interface PlanApiService {
    @GET("plans")
    suspend fun getPlans(): List<PlanDto>
}
