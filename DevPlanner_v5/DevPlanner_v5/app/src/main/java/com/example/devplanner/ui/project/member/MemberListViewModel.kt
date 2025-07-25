package com.example.devplanner.ui.project.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devplanner.data.local.entity.MemberEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MemberListViewModel : ViewModel() {
    private val _members = MutableStateFlow<List<MemberEntity>>(emptyList())
    val members: StateFlow<List<MemberEntity>> = _members

    fun loadMembers(projectId: Long) {
        viewModelScope.launch {
            _members.update { dummyMembers(projectId) }
        }
    }

    private fun dummyMembers(projectId: Long) = listOf(
        MemberEntity(projectId = projectId, name = "Alice", role = "Desarrollador", capacityHours = 40),
        MemberEntity(projectId = projectId, name = "Bob", role = "QA", capacityHours = 20)
    )
}