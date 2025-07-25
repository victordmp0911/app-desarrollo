package com.example.devplanner.data.repository

import com.example.devplanner.data.local.dao.ProjectDao
import com.example.devplanner.data.local.entity.ProjectEntity
import com.example.devplanner.data.local.entity.ProjectStatus
import com.example.devplanner.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {
    override fun getProjects(status: ProjectStatus): Flow<List<ProjectEntity>> =
        projectDao.getByStatus(status)

    override suspend fun createProject(project: ProjectEntity): Long =
        projectDao.insert(project)
}