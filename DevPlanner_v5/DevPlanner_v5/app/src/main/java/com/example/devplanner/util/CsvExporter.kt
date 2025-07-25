package com.example.devplanner.util

import com.example.devplanner.data.local.entity.TaskEntity
import java.io.File

object CsvExporter {
    fun exportTasksToCsv(tasks: List<TaskEntity>, file: File): Boolean {
        return try {
            val header = "ID,Title,Description,Status,EstimateHours,SpentHours"
            val lines = tasks.joinToString("\n") { t ->
                "${t.id},${t.title},${t.description ?: ""},${t.status},${t.estimateHours},${t.spentHours}"
            }
            file.writeText(header + "\n" + lines)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}