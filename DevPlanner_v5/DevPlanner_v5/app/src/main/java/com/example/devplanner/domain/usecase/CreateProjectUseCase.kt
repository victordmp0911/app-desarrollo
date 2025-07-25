package com.example.devplanner.domain.usecase

import com.example.devplanner.data.local.entity.ProjectEntity
import com.example.devplanner.domain.repository.ProjectRepository
import javax.inject.Inject

class CreateProjectUseCase @Inject constructor(
    private val repository: ProjectRepository
) {
    suspend operator fun invoke(project: ProjectEntity): Long =
        repository.createProject(project)
}