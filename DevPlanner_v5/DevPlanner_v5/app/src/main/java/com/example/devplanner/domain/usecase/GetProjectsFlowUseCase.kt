package com.example.devplanner.domain.usecase

import com.example.devplanner.data.local.entity.ProjectEntity
import com.example.devplanner.data.local.entity.ProjectStatus
import com.example.devplanner.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProjectsFlowUseCase @Inject constructor(
    private val repository: ProjectRepository
) {
    operator fun invoke(status: ProjectStatus = ProjectStatus.ACTIVE): Flow<List<ProjectEntity>> =
        repository.getProjects(status)
}