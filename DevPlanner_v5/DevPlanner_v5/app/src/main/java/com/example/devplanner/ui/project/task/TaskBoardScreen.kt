package com.example.devplanner.ui.project.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.devplanner.data.local.entity.TaskStatus

@Composable
fun TaskBoardScreen(
    viewModel: TaskBoardViewModel
) {
    val tasks by viewModel.tasks.collectAsState()

    Row(Modifier.fillMaxSize().padding(8.dp)) {
        Column(Modifier.weight(1f).padding(4.dp)) {
            Text("TODO", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(tasks.filter { it.status == TaskStatus.TODO }.size) { idx ->
                    val t = tasks.filter { it.status == TaskStatus.TODO }[idx]
                    Card(Modifier.fillMaxWidth().padding(4.dp)) {
                        Column(Modifier.padding(8.dp)) {
                            Text(t.title)
                            t.description?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
                        }
                    }
                }
            }
        }
        Column(Modifier.weight(1f).padding(4.dp)) {
            Text("DOING", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(tasks.filter { it.status == TaskStatus.DOING }.size) { idx ->
                    val t = tasks.filter { it.status == TaskStatus.DOING }[idx]
                    Card(Modifier.fillMaxWidth().padding(4.dp)) {
                        Column(Modifier.padding(8.dp)) {
                            Text(t.title)
                            t.description?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
                        }
                    }
                }
            }
        }
        Column(Modifier.weight(1f).padding(4.dp)) {
            Text("DONE", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(tasks.filter { it.status == TaskStatus.DONE }.size) { idx ->
                    val t = tasks.filter { it.status == TaskStatus.DONE }[idx]
                    Card(Modifier.fillMaxWidth().padding(4.dp)) {
                        Column(Modifier.padding(8.dp)) {
                            Text(t.title)
                            t.description?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
                        }
                    }
                }
            }
        }
    }
}