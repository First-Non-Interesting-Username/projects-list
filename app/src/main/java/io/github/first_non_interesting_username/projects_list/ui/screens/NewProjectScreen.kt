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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.first_non_interesting_username.projects_list.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProjectScreen(navController: NavHostController) {
    var priorityFloat by remember { mutableFloatStateOf(0f) }
    var motivationFloat by remember { mutableFloatStateOf(0f) }
    var isEditing by remember { mutableStateOf(false) }
    var linkText by remember { mutableStateOf("test") }

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
                label = { Text("Name")},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                state = rememberTextFieldState(),
                label = { Text("Description") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
                lineLimits = TextFieldLineLimits.MultiLine(
                    minHeightInLines = 3,
                    maxHeightInLines = 6
                )
            )
            Spacer(Modifier.height(8.dp))
            SliderWithDescription(
                value = priorityFloat,
                onValueChange = { priorityFloat = it },
                text = "Priority",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
                textModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
                keyModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
                )
            Spacer(Modifier.height(8.dp))
            SliderWithDescription(
                value = motivationFloat,
                onValueChange = { motivationFloat = it },
                text = "Motivation",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
                textModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
                keyModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
            )
            Spacer(Modifier.height(8.dp))
            LinkRow(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
                initialText = linkText,
                onValueChange = {linkText = it},
                isEditing = isEditing,
                onEditClick = {isEditing = !isEditing},
            )
        }
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

    Text(text = text,
        modifier = textModifier)
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

@Composable
fun LinkRow(
    modifier: Modifier,
    initialText: String,
    onValueChange: (String) -> Unit,
    isEditing: Boolean,
    onEditClick: (Boolean) -> Unit,
) {
    Row(modifier = modifier,) {
        if (isEditing) {
            OutlinedTextField(
                value = initialText,
                onValueChange = onValueChange,
            )
        } else {
            Text(
                text = initialText,
                modifier = Modifier
                    .weight(1f)
                    .clickable {

                }
            )
        }
        Icon(
            painter = painterResource(R.drawable.ic_edit),
            contentDescription = "Back",
            modifier = Modifier.clickable {
                onEditClick
            }
        )
    }

}