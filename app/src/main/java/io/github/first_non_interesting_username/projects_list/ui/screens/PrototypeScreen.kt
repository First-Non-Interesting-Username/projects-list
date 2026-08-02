package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import io.github.first_non_interesting_username.projects_list.ui.components.ProjectRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrototypeScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        ProjectRow(
            name = "Name",
            isFavourite = false,
            modifier = Modifier.padding(innerPadding),
            priority = 1f,
            motivation = 1f,
            chronology = 1
        )
    }
}