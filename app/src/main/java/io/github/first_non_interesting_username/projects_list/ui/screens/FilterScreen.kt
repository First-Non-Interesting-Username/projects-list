package io.github.first_non_interesting_username.projects_list.ui.screens

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.first_non_interesting_username.projects_list.R
import io.github.first_non_interesting_username.projects_list.ui.components.SimpleTopBar
import kotlin.math.roundToInt
import io.github.first_non_interesting_username.projects_list.ui.components.ActionButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavHostController) {



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
            DoubleCheckboxRow(
                firstName = "favorite",
                firstValue = false,
                secondValue = false,
                secondName = "non favorite",
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
fun DoubleCheckboxRow(
    firstValue: Boolean,
    firstName: String,
    secondValue: Boolean,
    secondName: String,
) {
    var firstChecked by remember { mutableStateOf(value = firstValue) }
    var secondChecked by remember { mutableStateOf(value = secondValue) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = firstChecked,
            onCheckedChange = { firstChecked = it }
        )
        Spacer(Modifier.width(8.dp))
        Text(text = firstName)
        Spacer(Modifier.width(8.dp))
        Checkbox(
            checked = secondChecked,
            onCheckedChange = { secondChecked = it }
        )
        Spacer(Modifier.width(8.dp))
        Text(text = secondName)
    }
}