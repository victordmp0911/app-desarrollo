package com.example.devplanner.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String?,
    val scope: String?,
    val startDate: Long,
    val endDate: Long?,
    val status: ProjectStatus = ProjectStatus.ACTIVE
)

enum class ProjectStatus { ACTIVE, ARCHIVED }