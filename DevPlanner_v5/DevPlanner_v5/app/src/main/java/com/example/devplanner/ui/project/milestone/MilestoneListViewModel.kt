package com.example.devplanner.ui.project.milestone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devplanner.data.local.entity.MilestoneEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MilestoneListViewModel : ViewModel() {
    private val _milestones = MutableStateFlow<List<MilestoneEntity>>(emptyList())
    val milestones: StateFlow<List<MilestoneEntity>> = _milestones

    fun loadMilestones(projectId: Long) {
        viewModelScope.launch {
            _milestones.update { dummyMilestones(projectId) }
        }
    }

    private fun dummyMilestones(projectId: Long) = listOf(
        MilestoneEntity(projectId = projectId, name = "Módulo 1", dueDate = System.currentTimeMillis() + 86400000, progress = 60),
        MilestoneEntity(projectId = projectId, name = "Módulo 2", dueDate = System.currentTimeMillis() + 172800000, progress = 30)
    )
}