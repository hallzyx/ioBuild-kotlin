package com.example.iobuild_kt.core.di

import com.example.iobuild_kt.core.data.TokenManager
import org.koin.dsl.module

val dataModule = module {
    single { TokenManager(get()) }
}
