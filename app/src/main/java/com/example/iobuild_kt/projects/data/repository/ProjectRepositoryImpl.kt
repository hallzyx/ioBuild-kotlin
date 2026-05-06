package com.example.iobuild_kt.projects.data.repository

import com.example.iobuild_kt.projects.data.api.ProjectApiService
import com.example.iobuild_kt.projects.data.dto.toCreateRequest
import com.example.iobuild_kt.projects.data.dto.toDomain
import com.example.iobuild_kt.projects.data.dto.toUpdateRequest
import com.example.iobuild_kt.projects.domain.model.Project
import com.example.iobuild_kt.projects.domain.repository.ProjectRepository
import java.io.IOException

class ProjectRepositoryImpl(
    private val api: ProjectApiService
) : ProjectRepository {

    override suspend fun getProjects(): Result<List<Project>> = runCatching {
        api.getAllProjects().map { it.toDomain() }
    }

    override suspend fun getProjectById(id: Int): Result<Project> = runCatching {
        api.getProjectById(id).toDomain()
    }

    override suspend fun createProject(project: Project): Result<Project> = runCatching {
        api.createProject(project.toCreateRequest()).toDomain()
    }

    override suspend fun updateProject(project: Project): Result<Project> = runCatching {
        api.updateProject(project.id, project.toUpdateRequest()).toDomain()
    }

    override suspend fun deleteProject(id: Int): Result<Unit> = runCatching {
        api.deleteProject(id)
    }
}
