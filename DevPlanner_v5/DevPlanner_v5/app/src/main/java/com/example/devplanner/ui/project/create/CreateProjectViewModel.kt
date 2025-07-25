package com.example.devplanner.ui.project.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devplanner.data.local.entity.ProjectEntity
import com.example.devplanner.domain.usecase.CreateProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateProjectUiState(
    val name: String = "",
    val description: String = "",
    val scope: String = "",
    val startDate: Long = System.currentTimeMillis(),
    val endDate: Long? = null,
    val isSaving: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val createProject: CreateProjectUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateProjectUiState())
    val uiState: StateFlow<CreateProjectUiState> = _uiState.asStateFlow()

    fun onNameChanged(v: String) = _uiState.update { it.copy(name = v) }
    fun onDescriptionChanged(v: String) = _uiState.update { it.copy(description = v) }
    fun onScopeChanged(v: String) = _uiState.update { it.copy(scope = v) }

    fun save(onSaved: () -> Unit) = viewModelScope.launch {
        try {
            _uiState.update { it.copy(isSaving = true, error = null) }
            val p = ProjectEntity(
                name = _uiState.value.name,
                description = _uiState.value.description.ifBlank { null },
                scope = _uiState.value.scope.ifBlank { null },
                startDate = _uiState.value.startDate,
                endDate = _uiState.value.endDate
            )
            createProject(p)
            onSaved()
        } catch (e: Exception) {
            _uiState.update { it.copy(error = e.message ?: "Error desconocido") }
        } finally {
            _uiState.update { it.copy(isSaving = false) }
        }
    }
}