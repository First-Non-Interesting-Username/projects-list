package io.github.first_non_interesting_username.projects_list.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.first_non_interesting_username.projects_list.util.openUrl


@Composable
fun ProjectDisplayColumn(
    nameValue: String,
    descriptionValue: String,
    linkValue: String,
    priorityValue: Float,
    motivationValue: Float,
    modifier: Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .padding(4.dp),
        ) ) {
        MyTextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = nameValue,
            label = "Name"
            )
        Spacer(Modifier.height(8.dp))
        MyTextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = descriptionValue,
            label = "Description",
            textMinLines = 3,
            textMaxLines = 6,
        )
        Spacer(Modifier.height(8.dp))
        MyTextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = linkValue,
            label = "Link",
            onClick = {context.openUrl("example.com")}
        )
    }
}

