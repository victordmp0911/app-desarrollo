package com.example.devplanner.ui.project.task.dnd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devplanner.data.local.entity.TaskEntity
import com.example.devplanner.data.local.entity.TaskPriority
import com.example.devplanner.data.local.entity.TaskStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskBoardDnDViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    fun load(projectId: Long) {
        viewModelScope.launch {
            _tasks.update { dummy(projectId) }
        }
    }

    fun moveToStatus(task: TaskEntity, newStatus: TaskStatus) {
        _tasks.update { list ->
            list.map {
                if (it.id == task.id) it.copy(status = newStatus) else it
            }
        }
    }

    private fun dummy(projectId: Long): List<TaskEntity> = listOf(
        TaskEntity(id = 1, projectId = projectId, assigneeId = null, title = "Plan", description = null, estimateHours = 4, spentHours = 0, startDate = null, dueDate = null, priority = TaskPriority.MEDIUM, status = TaskStatus.TODO),
        TaskEntity(id = 2, projectId = projectId, assigneeId = null, title = "Model", description = null, estimateHours = 6, spentHours = 2, startDate = null, dueDate = null, priority = TaskPriority.HIGH, status = TaskStatus.DOING),
        TaskEntity(id = 3, projectId = projectId, assigneeId = null, title = "UI", description = null, estimateHours = 3, spentHours = 3, startDate = null, dueDate = null, priority = TaskPriority.LOW, status = TaskStatus.DONE)
    )
}