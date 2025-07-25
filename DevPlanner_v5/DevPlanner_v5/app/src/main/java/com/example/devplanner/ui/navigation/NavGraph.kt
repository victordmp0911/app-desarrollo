package com.example.devplanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.devplanner.ui.project.create.CreateProjectScreen
import com.example.devplanner.ui.project.create.CreateProjectViewModel
import com.example.devplanner.ui.project.list.ProjectListScreen
import com.example.devplanner.ui.project.list.ProjectListViewModel
import com.example.devplanner.ui.settings.SettingsScreen
import com.example.devplanner.ui.settings.SettingsViewModel
import com.example.devplanner.ui.project.task.dnd.TaskBoardDnDScreen
import com.example.devplanner.ui.project.task.dnd.TaskBoardDnDViewModel
import com.example.devplanner.ui.project.task.TaskBoardScreen
import com.example.devplanner.ui.project.task.TaskBoardViewModel

object Routes {
    const val PROJECT_LIST = "project_list"
    const val PROJECT_CREATE = "project_create"
    const val TASK_BOARD = "task_board"
    const val TASK_BOARD_DND = "task_board_dnd"
    const val SETTINGS = "settings"
}

@Composable
fun DevPlannerNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.PROJECT_LIST) {

        composable(Routes.PROJECT_LIST) {
            val vm: ProjectListViewModel = hiltViewModel()
            ProjectListScreen(
                viewModel = vm,
                onCreateProject = { navController.navigate(Routes.PROJECT_CREATE) }
            )
        }

        composable(Routes.PROJECT_CREATE) {
            val vm: CreateProjectViewModel = hiltViewModel()
            CreateProjectScreen(
                viewModel = vm,
                onSaved = { navController.popBackStack() }
            )
        }
    }
}