package com.example.devplanner.ui.project.task.dnd

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.devplanner.data.local.entity.TaskEntity
import com.example.devplanner.data.local.entity.TaskStatus

@Composable
fun TaskBoardDnDScreen(
    viewModel: TaskBoardDnDViewModel,
    projectId: Long = 1L
) {
    val tasks by viewModel.tasks.collectAsState()
    LaunchedEffect(projectId) { viewModel.load(projectId) }

    val todo = tasks.filter { it.status == TaskStatus.TODO }
    val doing = tasks.filter { it.status == TaskStatus.DOING }
    val done = tasks.filter { it.status == TaskStatus.DONE }

    Row(Modifier.fillMaxSize().padding(8.dp)) {
        TaskColumn(
            title = "TODO",
            tasks = todo,
            onDropTask = { viewModel.moveToStatus(it, TaskStatus.TODO) },
            modifier = Modifier.weight(1f)
        )
        TaskColumn(
            title = "DOING",
            tasks = doing,
            onDropTask = { viewModel.moveToStatus(it, TaskStatus.DOING) },
            modifier = Modifier.weight(1f)
        )
        TaskColumn(
            title = "DONE",
            tasks = done,
            onDropTask = { viewModel.moveToStatus(it, TaskStatus.DONE) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun TaskColumn(
    title: String,
    tasks: List<TaskEntity>,
    onDropTask: (TaskEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxHeight()
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )
        LazyColumn(Modifier.fillMaxSize()) {
            items(tasks.size) { idx ->
                val task = tasks[idx]
                DraggableTaskCard(task, onDropTask)
            }
        }
    }
}

@Composable
private fun DraggableTaskCard(
    task: TaskEntity,
    onDropTask: (TaskEntity) -> Unit
) {
    var offset by remember { mutableStateOf(Offset.Zero) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        // Aquí no sabemos en qué columna terminó exactamente sin coord global,
                        // así que este demo no cambia estado automáticamente.
                        // En una implementación real, deberías usar coordenadas y bounds de columnas.
                        // Por ahora, mostramos un snackbar/placeholder o llamamos manualmente onDropTask.
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offset += dragAmount
                }
            }
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(task.title, style = MaterialTheme.typography.titleMedium)
            if (!task.description.isNullOrBlank())
                Text(task.description!!, style = MaterialTheme.typography.bodySmall)
        }
    }
}