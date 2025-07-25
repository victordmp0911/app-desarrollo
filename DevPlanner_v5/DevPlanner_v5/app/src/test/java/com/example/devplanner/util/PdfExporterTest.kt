package com.example.devplanner.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.devplanner.data.local.entity.TaskEntity
import com.example.devplanner.data.local.entity.TaskStatus
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class PdfExporterTest {
    @Test
    fun exportTasksToPdf_createsNonEmptyFile() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val tasks = listOf(
            TaskEntity(id = 1, projectId = 1, assigneeId = null, title = "Task1", description = null, estimateHours = 1, spentHours = 0, startDate = null, dueDate = null, status = TaskStatus.TODO)
        )
        val tempFile = File.createTempFile("test", ".pdf").apply { deleteOnExit() }

        val result = PdfExporter.exportTasksToPdf(context, tasks, tempFile)

        assertTrue(result)
        assertTrue(tempFile.exists())
        assertTrue(tempFile.length() > 0)
    }
}
