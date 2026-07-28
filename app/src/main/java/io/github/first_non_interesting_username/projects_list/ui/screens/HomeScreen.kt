package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.first_non_interesting_username.projects_list.Routes
import kotlin.collections.iterator

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                onSettingsClick = { navController.navigate(Routes.SETTINGS) },
                onAboutClick = { navController.navigate(Routes.ABOUT) },
            )
        },
    ) { innerPadding ->
        MyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
fun TaskInputField(
    task: String,
    onTaskChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = task,
        onValueChange = onTaskChange,
        label = { Text("Task") },
        singleLine = true,
        modifier = modifier,
    )
}

@Composable
fun TaskDisplay(
    task: String,
    taskNumber: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier,
    ) {
        Text(
            text = "Task $taskNumber: $task",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ResetButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(onClick = onClick, modifier = modifier) {
        Text("Reset")
    }
}

@Composable
fun ConfirmationButton(onClick: () -> Unit) {
    FilledTonalButton(onClick = { onClick() }) {
        Text("Add task")
    }
}

@Composable
fun MyColumn(modifier: Modifier = Modifier) {
    val taskList = remember { mutableStateMapOf<Int, String>() }
    var nextId by remember { mutableStateOf(0) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TaskInputField(
            task = taskList.getOrPut(nextId) { "" },
            onTaskChange = { taskList[nextId] = it },
        )
        Spacer(Modifier.height(8.dp))
        ConfirmationButton(
            onClick = ({ nextId++ })
        )
        for ((taskIndex, task) in taskList) {
            if (task.isNotEmpty() && taskIndex != nextId) {
                Spacer(Modifier.height(8.dp))
                TaskDisplay(
                    task = task, taskNumber = taskIndex.toString()
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        ResetButton(onClick = {
            for ((taskIndex, task) in taskList) {
                taskList[taskIndex] = ""
                nextId = 0
            }
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(onSettingsClick: () -> Unit,
              onAboutClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Projects List") },
        modifier = Modifier,
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        expanded = false
                        onSettingsClick()
                    }
                )
                DropdownMenuItem(
                    text = { Text("About") },
                        onClick = {
                            expanded = false
                        onAboutClick()
                    }
                )
            }
        }
    )
}