package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.first_non_interesting_username.projects_list.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProjectScreen(navController: NavHostController) {
    var priorityFloat by remember { mutableFloatStateOf(0f) }
    var motivationFloat by remember { mutableFloatStateOf(0f) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Adding new project") },
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
                icon = painterResource(R.drawable.ic_add_task),
                contentDescription = "Add the project",
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp),
        ) {
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                state = rememberTextFieldState(),
                label = { Text("Name") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                state = rememberTextFieldState(),
                label = { Text("Description") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
                lineLimits = TextFieldLineLimits.MultiLine(
                    minHeightInLines = 3,
                    maxHeightInLines = 6
                )
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                state = rememberTextFieldState(),
                label = { Text("Link") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
            )
            Spacer(Modifier.height(8.dp))
            SliderWithDescription(
                value = priorityFloat,
                onValueChange = { priorityFloat = it },
                text = "Priority",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
                textModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
                keyModifier = Modifier

                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
            )
            Spacer(Modifier.height(8.dp))
            SliderWithDescription(
                value = motivationFloat,
                onValueChange = { motivationFloat = it },
                text = "Motivation",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
                textModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
                keyModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
            )
            Spacer(Modifier.height(8.dp))

        }
    }
}

@Composable
fun ActionButton(
    onClick: () -> Unit,
    icon: Painter,
    contentDescription: String,
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun SliderWithDescription(
    value: Float,
    modifier: Modifier,
    onValueChange: (Float) -> Unit,
    textModifier: Modifier,
    text: String,
    keyModifier: Modifier,
) {

    Text(
        text = text,
        modifier = textModifier
    )
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 0f..10f,
        steps = 9,
        modifier = modifier,
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = keyModifier,
    ) {
        for (i in 0..10) {
            Text(text = i.toString())
        }
    }
}
