package com.example.iobuild_kt.subscription.data.dto

data class PlanDto(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val features: List<String>,
    val maxDevices: Int,
    val maxAdministrators: Int,
    val supportLevel: String,
    val hasAPI: Boolean = false,
    val hasAnalytics: Boolean = false
)
