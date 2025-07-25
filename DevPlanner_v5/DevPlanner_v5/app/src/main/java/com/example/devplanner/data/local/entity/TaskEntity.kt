package com.example.devplanner.data.local.entity

import androidx.room.*

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(entity = ProjectEntity::class, parentColumns = ["id"], childColumns = ["projectId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = MemberEntity::class, parentColumns = ["id"], childColumns = ["assigneeId"], onDelete = ForeignKey.SET_NULL)
    ],
    indices = [Index("projectId"), Index("assigneeId")]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val projectId: Long,
    val assigneeId: Long?,
    val title: String,
    val description: String?,
    val estimateHours: Int,
    val spentHours: Int = 0,
    val startDate: Long?,
    val dueDate: Long?,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val status: TaskStatus = TaskStatus.TODO
)

enum class TaskPriority { LOW, MEDIUM, HIGH, CRITICAL }
enum class TaskStatus { TODO, DOING, DONE }