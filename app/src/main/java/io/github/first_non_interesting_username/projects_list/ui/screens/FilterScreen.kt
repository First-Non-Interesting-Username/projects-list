package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var one by remember { mutableStateOf(false) }
    var two by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ActionButton(
                onClick = {},
                icon = painterResource(R.drawable.ic_exit_to_app),
                contentDescription = "Apply filters",
            )
        },
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
                .padding(innerPadding)
                .padding(4.dp),
        ) {
            RangeSliderWithDescription(
                minValue = 0f,
                maxValue = 10f,
                name = "Motivation"
            )
            Spacer(modifier = Modifier.height(8.dp))
            RangeSliderWithDescription(
                minValue = 0f,
                maxValue = 10f,
                name = "Priority"
            )
            Spacer(modifier = Modifier.height(8.dp))
            TwoToggleRow(
                desc1 = "favorite",
                checked1 = one,
                onToggle1 = {one = !one},
                desc2 = "non favorite",
                checked2 = two,
                onToggle2 = {two = !two},
            )
            Spacer(modifier = Modifier.height(8.dp))
            TwoToggleRow(
                desc1 = "unfinished",
                checked1 = one,
                onToggle1 = {one = !one},
                desc2 = "finished",
                checked2 = two,
                onToggle2 = {two = !two},
            )
        }
    }
}


@Composable
fun RangeSliderWithDescription(
    minValue: Float,
    maxValue: Float,
    name: String,
) {
    var sliderPosition by remember { mutableStateOf(value = minValue..maxValue) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RangeSlider(
            value = sliderPosition,
            steps = 9,
            onValueChange = { range -> sliderPosition = range },
            valueRange = 0f..10f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.9f),
        )
        Text(
            text = "$name ${sliderPosition.start.roundToInt()} – ${sliderPosition.endInclusive.roundToInt()}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.9f),
        )

    }
}

@Composable
fun LabeledSwitch(
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(description)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun TwoToggleRow(
    desc1: String,
    checked1: Boolean,
    onToggle1: (Boolean) -> Unit,
    desc2: String,
    checked2: Boolean,
    onToggle2: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 24.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LabeledSwitch(desc1, checked1, onToggle1)
        LabeledSwitch(desc2, checked2, onToggle2)
    }
}