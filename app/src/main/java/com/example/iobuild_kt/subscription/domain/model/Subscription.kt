package com.example.iobuild_kt.subscription.domain.model

data class Subscription(
    val id: Int = 0,
    val builderId: Int = 0,
    val plan: Plan? = null,
    val status: String = "active",
    val startDate: String = "",
    val endDate: String = ""
)
