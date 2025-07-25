package com.example.devplanner.data.local.dao

import androidx.room.*
import com.example.devplanner.data.local.entity.ProjectEntity
import com.example.devplanner.data.local.entity.ProjectStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert
    suspend fun insert(project: ProjectEntity): Long

    @Update
    suspend fun update(project: ProjectEntity)

    @Query("SELECT * FROM projects WHERE status = :status ORDER BY startDate DESC")
    fun getByStatus(status: ProjectStatus = ProjectStatus.ACTIVE): Flow<List<ProjectEntity>>
}