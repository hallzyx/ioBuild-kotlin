package com.example.iobuild_kt.core.di

import com.example.iobuild_kt.auth.data.api.AuthApiService
import com.example.iobuild_kt.auth.data.repository.AuthRepositoryImpl
import com.example.iobuild_kt.auth.domain.repository.AuthRepository
import com.example.iobuild_kt.auth.presentation.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    single<AuthApiService> { get<Retrofit>().create(AuthApiService::class.java) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    viewModelOf(::LoginViewModel)
}

val appModule = listOf(networkModule, dataModule, authModule)
