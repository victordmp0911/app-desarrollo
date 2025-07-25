package com.example.devplanner.ui.project.milestone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MilestoneListScreen(viewModel: MilestoneListViewModel) {
    val milestones by viewModel.milestones.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Hitos", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(milestones.size) { idx ->
                val m = milestones[idx]
                Card(Modifier.fillMaxWidth().padding(4.dp)) {
                    Column(Modifier.padding(8.dp)) {
                        Text(m.name, style = MaterialTheme.typography.titleMedium)
                        Text("Progreso: ${m.progress}%", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}