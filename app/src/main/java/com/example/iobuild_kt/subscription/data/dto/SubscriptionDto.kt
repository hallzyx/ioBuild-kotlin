package com.example.iobuild_kt.subscription.data.dto

data class SubscriptionDto(
    val id: Int,
    val builderId: Int,
    val plan: PlanDto?,
    val status: String,
    val startDate: String?,
    val endDate: String?
)
