package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.first_non_interesting_username.projects_list.R
import io.github.first_non_interesting_username.projects_list.ui.components.ActionButton
import io.github.first_non_interesting_username.projects_list.ui.components.SimpleTopBar
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavHostController) {
    var motivation by remember { mutableStateOf(0f..10f) }
    var priority by remember { mutableStateOf(0f..10f) }
    var favorite by remember { mutableStateOf(true) }
    var nonFavorite by remember { mutableStateOf(true) }
    var unfinished by remember { mutableStateOf(true) }
    var finished by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ActionButton(
                onClick = {},
                icon = painterResource(R.drawable.ic_exit_to_app),
                contentDescription = "Apply filters"
            )
        },
        topBar = {
            SimpleTopBar(navController = navController, name = "Settings")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
        ) {
            FilterRange("Motivation", motivation) { motivation = it }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp)

            FilterRange("Priority", priority) { priority = it }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp)

            FilterToggles(
                left = "favorite" to favorite,
                right = "non favorite" to nonFavorite,
                onLeft = { favorite = !favorite },
                onRight = { nonFavorite = !nonFavorite }
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp)

            FilterToggles(
                left = "unfinished" to unfinished,
                right = "finished" to finished,
                onLeft = { unfinished = !unfinished },
                onRight = { finished = !finished }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterRange(
    name: String,
    range: ClosedFloatingPointRange<Float>,
    onChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        RangeSlider(
            value = range,
            onValueChange = onChange,
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Text(
            text = "$name ${range.start.roundToInt()} – ${range.endInclusive.roundToInt()}",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
    }
}

@Composable
private fun FilterToggles(
    left: Pair<String, Boolean>,
    right: Pair<String, Boolean>,
    onLeft: () -> Unit,
    onRight: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            ToggleSwitch(left.first, left.second, onLeft)
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            ToggleSwitch(right.first, right.second, onRight)
        }
    }
}

@Composable
private fun ToggleSwitch(label: String, checked: Boolean, onToggle: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label)
        Switch(checked = checked, onCheckedChange = { onToggle() })
    }
}
