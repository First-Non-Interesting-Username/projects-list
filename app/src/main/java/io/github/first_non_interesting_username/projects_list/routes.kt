package io.github.first_non_interesting_username.projects_list

object Routes {
    const val PROTOTYPE = "prototype"
    const val HOME = "home"
    const val SETTINGS = "settings"
    const val ABOUT = "about"
    const val LICENSES = "licenses"
    const val NEW_PROJECT = "new_project"
    const val PROJECT = "project"
    const val PROJECT_ID_ARG = "projectId"
    const val PROJECT_ROUTE = "$PROJECT/{$PROJECT_ID_ARG}"
    const val EDIT_PROJECT = "edit_project"
    const val EDIT_PROJECT_ROUTE = "$EDIT_PROJECT/{$PROJECT_ID_ARG}"
    const val NEW_TASK = "new_task"
    const val TASK = "task"
    const val EDIT_TASK = "edit_task"

    fun projectRoute(projectId: String) = "$PROJECT/$projectId"
    fun editProjectRoute(projectId: String) = "$EDIT_PROJECT/$projectId"
}
