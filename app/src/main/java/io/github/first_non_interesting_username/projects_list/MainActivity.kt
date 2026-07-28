package io.github.first_non_interesting_username.projects_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.first_non_interesting_username.projects_list.ui.theme.ProjectsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
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
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier,
    ) {
        Text(
            text = "Task: $task",
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
fun ConfirmationSwitch(
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    Switch(
        checked = value,
        onCheckedChange = onValueChange
    )
}


@Composable
fun MyColumn(modifier: Modifier = Modifier) {
    var taskList = remember { mutableStateMapOf<Int, String>() }
    var confirmTask by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TaskInputField(
            task = taskList.getOrPut(0) { "" },
            onTaskChange = { taskList[0] = it },
        )
        Spacer(Modifier.height(8.dp))
        ConfirmationSwitch(
            value = confirmTask,
            onValueChange = { confirmTask = it }
        )
        for ((taskIndex, task) in taskList) {
            Spacer(Modifier.height(8.dp))
            TaskDisplay(task = task)
        }
        Spacer(Modifier.height(8.dp))
        ResetButton(onClick = {
            for ((taskIndex, task) in taskList) {
                taskList[taskIndex] = ""
            }
        })
    }
}
