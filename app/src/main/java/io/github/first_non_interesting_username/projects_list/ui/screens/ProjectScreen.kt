package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import io.github.first_non_interesting_username.projects_list.ui.components.AlertDialogExample
import io.github.first_non_interesting_username.projects_list.ui.components.ConfirmationButton
import io.github.first_non_interesting_username.projects_list.ui.components.ProjectDisplayColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectScreen(navController: NavHostController) {
    var priorityFloat by remember { mutableFloatStateOf(0f) }
    var motivationFloat by remember { mutableFloatStateOf(0f) }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    var finished by remember { mutableStateOf(false) }
    var confirmationDialog by remember { mutableStateOf(false) }

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
            )
        },
        floatingActionButton = {
            ActionButton(
                onClick = {},
                icon = painterResource(R.drawable.ic_edit),
                contentDescription = "Edit the project",
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
                contentDescription = "Toggle state of completion of the project",
                onClick = { confirmationDialog= !confirmationDialog },
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
                dialogTitle = "Mark as finished",
                dialogText = "Do you want to mark project $name as finished?",
                icon = painterResource(R.drawable.ic_done_all)
            )
        }
    }
}

