package com.example.devplanner.util

import com.example.devplanner.data.local.entity.TaskEntity
import com.example.devplanner.data.local.entity.TaskStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class CsvExporterTest {
    @Test
    fun exportTasksToCsv_writesExpectedContent() {
        val tasks = listOf(
            TaskEntity(id = 1, projectId = 1, assigneeId = null, title = "Task1", description = "Desc1", estimateHours = 5, spentHours = 2, startDate = null, dueDate = null, status = TaskStatus.TODO),
            TaskEntity(id = 2, projectId = 1, assigneeId = null, title = "Task2", description = null, estimateHours = 3, spentHours = 0, startDate = null, dueDate = null, status = TaskStatus.DONE)
        )

        val tempFile = File.createTempFile("test", ".csv").apply { deleteOnExit() }

        val result = CsvExporter.exportTasksToCsv(tasks, tempFile)

        assertTrue(result)
        val expected = "ID,Title,Description,Status,EstimateHours,SpentHours\n" +
                tasks.joinToString("\n") { t -> "${t.id},${t.title},${t.description ?: ""},${t.status},${t.estimateHours},${t.spentHours}" }
        val actual = tempFile.readText()
        assertEquals(expected, actual)
    }
}
