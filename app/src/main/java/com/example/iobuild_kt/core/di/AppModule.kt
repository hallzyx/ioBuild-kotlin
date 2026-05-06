package com.example.iobuild_kt.core.di

import com.example.iobuild_kt.auth.data.api.AuthApiService
import com.example.iobuild_kt.auth.data.repository.AuthRepositoryImpl
import com.example.iobuild_kt.auth.domain.repository.AuthRepository
import com.example.iobuild_kt.auth.presentation.LoginViewModel
import com.example.iobuild_kt.dashboard.data.api.AnalyticsApiService
import com.example.iobuild_kt.dashboard.data.repository.AnalyticsRepositoryImpl
import com.example.iobuild_kt.dashboard.domain.repository.AnalyticsRepository
import com.example.iobuild_kt.dashboard.domain.usecase.GetBuilderDashboardUseCase
import com.example.iobuild_kt.dashboard.presentation.DashboardViewModel
import com.example.iobuild_kt.projects.data.api.ProjectApiService
import com.example.iobuild_kt.projects.data.repository.ProjectRepositoryImpl
import com.example.iobuild_kt.projects.domain.repository.ProjectRepository
import com.example.iobuild_kt.projects.domain.usecase.CreateProjectUseCase
import com.example.iobuild_kt.projects.domain.usecase.DeleteProjectUseCase
import com.example.iobuild_kt.projects.domain.usecase.GetProjectByIdUseCase
import com.example.iobuild_kt.projects.domain.usecase.GetProjectsUseCase
import com.example.iobuild_kt.projects.domain.usecase.UpdateProjectUseCase
import com.example.iobuild_kt.projects.presentation.project_detail.ProjectDetailViewModel
import com.example.iobuild_kt.projects.presentation.project_form.ProjectFormViewModel
import com.example.iobuild_kt.projects.presentation.project_list.ProjectListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    single<AuthApiService> { get<Retrofit>().create(AuthApiService::class.java) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    viewModelOf(::LoginViewModel)
}

val analyticsModule = module {
    single<AnalyticsApiService> { get<Retrofit>().create(AnalyticsApiService::class.java) }
    single<AnalyticsRepository> { AnalyticsRepositoryImpl(get()) }
    factory { GetBuilderDashboardUseCase(get()) }
    viewModelOf(::DashboardViewModel)
}

val projectsModule = module {
    single<ProjectApiService> { get<Retrofit>().create(ProjectApiService::class.java) }
    single<ProjectRepository> { ProjectRepositoryImpl(get()) }
    factory { GetProjectsUseCase(get()) }
    factory { GetProjectByIdUseCase(get()) }
    factory { CreateProjectUseCase(get()) }
    factory { UpdateProjectUseCase(get()) }
    factory { DeleteProjectUseCase(get()) }
    viewModelOf(::ProjectListViewModel)
    viewModelOf(::ProjectDetailViewModel)
    viewModelOf(::ProjectFormViewModel)
}

val appModule = listOf(networkModule, dataModule, authModule, analyticsModule, projectsModule)
