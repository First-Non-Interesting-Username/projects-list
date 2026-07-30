package io.github.first_non_interesting_username.projects_list.data.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Task(
    val uuid: String = UUID.randomUUID().toString(),
    val chronology: Int,
    val title: String,
    val description: String = "",
    val link: String,
    val priority: Int,
    val motivation: Int,
) {
    init {
        require(priority in 0..10) { "priority must be 0–10, got $priority" }
        require(motivation in 0..10) { "motivation must be 0–10, got $motivation" }
    }
}