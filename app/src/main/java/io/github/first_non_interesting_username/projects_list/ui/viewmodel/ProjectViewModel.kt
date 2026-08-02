package io.github.first_non_interesting_username.projects_list.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.github.first_non_interesting_username.projects_list.ProjectsApplication
import io.github.first_non_interesting_username.projects_list.data.model.Project
import io.github.first_non_interesting_username.projects_list.data.model.Task
import io.github.first_non_interesting_username.projects_list.data.model.withNewTask
import io.github.first_non_interesting_username.projects_list.data.model.withUpdatedTask
import io.github.first_non_interesting_username.projects_list.data.model.withoutTask
import io.github.first_non_interesting_username.projects_list.data.repository.ProjectRepository
import kotlinx.coroutines.flow.StateFlow

class ProjectViewModel(private val repository: ProjectRepository) : ViewModel() {

    val projects: StateFlow<List<Project>> = repository.projects

    fun addProject(
        chronology: Int,
        title: String,
        description: String = "",
        link: String,
        priority: Float,
        motivation: Float,
        favorite: Boolean = false
    ) {
        repository.addProject(
            Project(
                chronology = chronology,
                title = title,
                description = description,
                link = link,
                priority = priority,
                motivation = motivation,
                favorite = favorite
            )
        )
    }

    fun updateProject(updated: Project) = repository.updateProject(updated)

    fun deleteProject(uuid: String) = repository.deleteProject(uuid)

    fun addTask(projectUuid: String, task: Task) {
        findProject(projectUuid)?.let { repository.updateProject(it.withNewTask(task)) }
    }

    fun updateTask(projectUuid: String, task: Task) {
        findProject(projectUuid)?.let { repository.updateProject(it.withUpdatedTask(task)) }
    }

    fun deleteTask(projectUuid: String, taskUuid: String) {
        findProject(projectUuid)?.let { repository.updateProject(it.withoutTask(taskUuid)) }
    }

    fun exportData(): String = repository.exportJson()

    fun importData(jsonString: String) = repository.importJson(jsonString)

    private fun findProject(id: String) = projects.value.find { it.uuid == id }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as ProjectsApplication
                ProjectViewModel(ProjectRepository(application))
            }
        }
    }
}
