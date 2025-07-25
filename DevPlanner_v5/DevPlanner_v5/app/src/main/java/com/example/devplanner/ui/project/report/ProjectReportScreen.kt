package com.example.devplanner.ui.project.report

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.devplanner.data.local.entity.TaskEntity
import com.example.devplanner.data.local.entity.TaskStatus

@Composable
fun ProjectReportScreen(tasks: List<TaskEntity>) {
    val total = tasks.size
    val done = tasks.count { it.status == TaskStatus.DONE }
    val progress = if (total > 0) (done * 100 / total) else 0

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Reporte de Proyecto", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text("Tareas completadas: $done / $total")
        Spacer(Modifier.height(4.dp))
        LinearProgressIndicator(progress / 100f, modifier = Modifier.fillMaxWidth().height(8.dp))
        Spacer(Modifier.height(8.dp))
        Text("Progreso: $progress%")
    }
}