package com.example.devplanner.ui.project.member

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MemberListScreen(viewModel: MemberListViewModel) {
    val members by viewModel.members.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Miembros", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(members.size) { idx ->
                val m = members[idx]
                Card(Modifier.fillMaxWidth().padding(4.dp)) {
                    Column(Modifier.padding(8.dp)) {
                        Text(m.name, style = MaterialTheme.typography.titleMedium)
                        Text("Rol: ${m.role} - Capacidad: ${m.capacityHours}h/semana", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}