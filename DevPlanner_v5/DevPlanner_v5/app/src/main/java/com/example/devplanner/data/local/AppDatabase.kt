package com.example.devplanner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.devplanner.data.local.dao.ProjectDao
import com.example.devplanner.data.local.entity.MemberEntity
import com.example.devplanner.data.local.entity.ProjectEntity
import com.example.devplanner.data.local.entity.TaskEntity
import com.example.devplanner.data.local.entity.MilestoneEntity

@Database(
    entities = [
        ProjectEntity::class,
        MemberEntity::class,
        TaskEntity::class,
        MilestoneEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}
