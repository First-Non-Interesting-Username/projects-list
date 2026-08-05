package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.first_non_interesting_username.projects_list.R
import io.github.first_non_interesting_username.projects_list.ui.components.SimpleTopBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SimpleTopBar(
                navController = navController,
                name = "Settings"
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(4.dp),
        ) {
            // SectionHeader(text = "Data")
            ActionRow(
                icon = painterResource(R.drawable.ic_upload),
                title = "Export data ",
                subtitle = "Export all projects and tasks",
                enabled = true,
                onClick = {  }
            )
            Spacer(Modifier.height(8.dp))
            ActionRow(
                icon = painterResource(R.drawable.ic_download),
                title = "Import data",
                subtitle = "Import data from a backup",
                enabled = true,
                onClick = {  }
            )
            Spacer(Modifier.height(8.dp))
            ActionRow(
                icon = painterResource(R.drawable.ic_delete),
                title = "Prune finished",
                subtitle = "Delete all finished projects and tasks",
                enabled = true,
                onClick = {  }
            )
            Spacer(Modifier.height(8.dp))
            ActionRow(
                icon = painterResource(R.drawable.ic_delete_forever),
                title = "Delete all data",
                subtitle = "Delete all projects and tasks",
                enabled = true,
                onClick = {  }
            )
            Spacer(Modifier.height(8.dp))
            ActionRow(
                icon = painterResource(R.drawable.ic_storage),
                title = "Used storage: 500GB",
                subtitle = "Something went very wrong if it's above 10MB",
                enabled = true,
                onClick = {  }
            )
        }
    }
}