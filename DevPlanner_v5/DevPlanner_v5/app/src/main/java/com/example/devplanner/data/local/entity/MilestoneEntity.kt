package com.example.devplanner.data.local.entity

import androidx.room.*

@Entity(
    tableName = "milestones",
    foreignKeys = [ForeignKey(
        entity = ProjectEntity::class,
        parentColumns = ["id"],
        childColumns = ["projectId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("projectId")]
)
data class MilestoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val projectId: Long,
    val name: String,
    val dueDate: Long,
    val progress: Int = 0 // 0-100
)