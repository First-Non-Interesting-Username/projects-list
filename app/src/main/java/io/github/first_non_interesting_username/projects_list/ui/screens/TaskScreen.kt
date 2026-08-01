package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.first_non_interesting_username.projects_list.R
import io.github.first_non_interesting_username.projects_list.ui.components.ActionButton
import io.github.first_non_interesting_username.projects_list.ui.components.AlertDialogExample
import io.github.first_non_interesting_username.projects_list.ui.components.ConfirmationButton
import io.github.first_non_interesting_username.projects_list.ui.components.ProjectDisplayColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavHostController) {
    var priorityFloat by remember { mutableFloatStateOf(0f) }
    var motivationFloat by remember { mutableFloatStateOf(0f) }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    var finished by remember { mutableStateOf(false) }
    var confirmationDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var deletionDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(name) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = "More options"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = {
                                expanded = false
                                deletionDialog = !deletionDialog
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ActionButton(
                onClick = {},
                icon = painterResource(R.drawable.ic_edit),
                contentDescription = "Edit the task",
            )
        }
    ) { innerPadding ->
        Column(Modifier.verticalScroll(rememberScrollState())) {
            ProjectDisplayColumn(
                modifier = Modifier.padding(innerPadding),
                nameValue = name,
                descriptionValue = description,
                linkValue = link,
                priorityValue = priorityFloat,
                motivationValue = motivationFloat,
            )
            Spacer(Modifier.height(8.dp))
            ConfirmationButton(
                icon = painterResource(R.drawable.ic_done_all),
                text = "Mark as finished",
                clickedText = "Mark as unfinished",
                contentDescription = "Toggle state of completion of the task",
                onClick = { confirmationDialog = !confirmationDialog },
                clicked = finished
            )
        }
        if (confirmationDialog) {
            AlertDialogExample(
                onDismissRequest = { confirmationDialog = !confirmationDialog },
                onConfirmation = {
                    confirmationDialog = !confirmationDialog
                    finished = !finished
                },
                dialogTitle = if (!finished) {
                    "Mark as finished"
                } else {
                    "Mark as unfinished"
                },
                dialogText = if (!finished) {
                    "Do you want to mark task $name as finished?"
                } else {
                    "Do you want to mark task $name as unfinished?"
                },
                icon = painterResource(R.drawable.ic_done_all)
            )
        }
        if (deletionDialog) {
            AlertDialogExample(
                onDismissRequest = { deletionDialog = !deletionDialog },
                onConfirmation = {
                    deletionDialog = !deletionDialog
                },
                dialogTitle = "Delete $name",
                dialogText = """
                    Do you want to delete task $name?
                    This action is irreversible.
                """.trimIndent(),
                icon = painterResource(R.drawable.ic_delete_forever)
            )
        }
    }
}

