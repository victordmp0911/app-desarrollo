package com.example.devplanner.ui.project.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devplanner.data.local.entity.TaskEntity
import com.example.devplanner.data.local.entity.TaskStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskBoardViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    fun loadTasksForProject(projectId: Long) {
        // TODO: load from repository
        viewModelScope.launch {
            _tasks.update { dummyTasks() }
        }
    }

    fun dummyTasks(): List<TaskEntity> = listOf(
        TaskEntity(projectId = 1, assigneeId = null, title = "Análisis", description = "Requerimientos", estimateHours = 4, startDate = System.currentTimeMillis(), dueDate = null, status = TaskStatus.TODO),
        TaskEntity(projectId = 1, assigneeId = null, title = "Diseño BD", description = "Modelar esquema", estimateHours = 6, startDate = System.currentTimeMillis(), dueDate = null, status = TaskStatus.DOING),
        TaskEntity(projectId = 1, assigneeId = null, title = "Login UI", description = "Pantalla de login", estimateHours = 3, startDate = System.currentTimeMillis(), dueDate = null, status = TaskStatus.DONE)
    )
}