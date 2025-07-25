package com.example.devplanner.domain.repository

import com.example.devplanner.data.local.entity.ProjectEntity
import com.example.devplanner.data.local.entity.ProjectStatus
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    fun getProjects(status: ProjectStatus = ProjectStatus.ACTIVE): Flow<List<ProjectEntity>>
    suspend fun createProject(project: ProjectEntity): Long
}