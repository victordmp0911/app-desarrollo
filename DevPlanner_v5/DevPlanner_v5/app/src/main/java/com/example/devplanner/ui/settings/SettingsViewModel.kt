package com.example.devplanner.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class SettingsState(
    val enableNotifications: Boolean = true,
    val exportFolder: String = ""
)

class SettingsViewModel : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state

    fun toggleNotifications(enabled: Boolean) {
        _state.update { it.copy(enableNotifications = enabled) }
    }

    fun setExportFolder(path: String) {
        _state.update { it.copy(exportFolder = path) }
    }
}