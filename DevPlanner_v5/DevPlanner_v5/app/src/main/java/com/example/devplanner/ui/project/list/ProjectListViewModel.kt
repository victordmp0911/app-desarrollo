package com.example.devplanner.ui.project.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devplanner.data.local.entity.ProjectEntity
import com.example.devplanner.data.local.entity.ProjectStatus
import com.example.devplanner.domain.usecase.GetProjectsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject constructor(
    getProjectsFlow: GetProjectsFlowUseCase
) : ViewModel() {

    val projects: StateFlow<List<ProjectEntity>> =
        getProjectsFlow(ProjectStatus.ACTIVE)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}