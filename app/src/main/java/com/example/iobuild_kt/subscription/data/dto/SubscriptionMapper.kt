package com.example.iobuild_kt.subscription.data.dto

import com.example.iobuild_kt.subscription.domain.model.Plan
import com.example.iobuild_kt.subscription.domain.model.Subscription

fun PlanDto.toDomain() = Plan(
    id = id, name = name, price = price, description = description,
    features = features, maxDevices = maxDevices,
    maxAdministrators = maxAdministrators, supportLevel = supportLevel,
    hasApi = hasAPI, hasAnalytics = hasAnalytics
)

fun SubscriptionDto.toDomain() = Subscription(
    id = id, builderId = builderId,
    plan = plan?.toDomain(), status = status,
    startDate = startDate ?: "", endDate = endDate ?: ""
)
