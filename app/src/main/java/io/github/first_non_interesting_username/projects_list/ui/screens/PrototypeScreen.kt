package io.github.first_non_interesting_username.projects_list.ui.screens

import androidx.appcompat.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrototypeScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        ProjectRow(
            name = "Name",
            isFavourite = false,
            modifier = Modifier.padding(innerPadding),
            priority = TODO(),
            motivation = TODO(),
            chronology = TODO()
        )
    }
}

@Composable
fun ProjectRow(
    name: String,
    isFavourite: Boolean,
    modifier: Modifier,
    priority: Float,
    motivation: Float,
    chronology: Int,
) {
    Box(
        modifier = modifier.then(
            Modifier.fillMaxSize())
        ,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(64.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#$chronology"
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "$motivation/10",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "$priority/10",
                style = MaterialTheme.typography.bodyLarge
            )
            if (isFavourite) {
                Icon(
                    painter = painterResource(R.drawable.ic_filled_star),
                )
            }
        }
    }
}