package com.example.devplanner.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Configuración", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Notificaciones")
            Switch(
                checked = state.enableNotifications,
                onCheckedChange = { viewModel.toggleNotifications(it) }
            )
        }

        Spacer(Modifier.height(16.dp))
        Text("Carpeta exportación (placeholder)")
        OutlinedTextField(
            value = state.exportFolder,
            onValueChange = viewModel::setExportFolder,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("/storage/emulated/0/Download") }
        )
    }
}