package io.github.first_non_interesting_username.projects_list.ui.screens

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import io.github.first_non_interesting_username.projects_list.R
import io.github.first_non_interesting_username.projects_list.BuildConfig
import android.content.pm.PackageManager
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("About") },
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
                .padding(16.dp),
        ) {
            MainSection(navController = navController)
        }
    }
}

@Composable
fun ActionRow(
    icon: Painter,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f),
            modifier = Modifier.size(28.dp),
        )

        Spacer(modifier = Modifier.width(24.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = subtitle,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
    )
}

fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}

@Composable
fun MainSection(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SectionHeader(text = "main")
        ActionRow(
            // Placeholder icon
            icon = painterResource(R.drawable.ic_public),
            title = "Projects List",
            subtitle = "A simple app for side project management",
            enabled = true,
            onClick = {}, // Add some easter egg here
        )
        Spacer(Modifier.height(8.dp))
        ActionRow(
            icon = painterResource(R.drawable.ic_info),
            title = "Version",
            subtitle = "v${BuildConfig.VERSION_NAME} (Build: ${BuildConfig.GIT_HASH})",
            enabled = true,
            onClick = {
                scope.launch {
                    val clipData = ClipData.newPlainText("Projects app git hash", BuildConfig.GIT_HASH)
                    clipboard.setClipEntry(ClipEntry(clipData))
                }
            },
            )
        Spacer(Modifier.height(8.dp))
        ActionRow(
            icon = painterResource(R.drawable.ic_code),
            title = "Homepage",
            subtitle = "Source code and app info",
            enabled = true,
            onClick = { openUrl(context, "https://github.com/First-Non-Interesting-Username/projects-list") },
        )
        Spacer(Modifier.height(8.dp))
        ActionRow(
            icon = painterResource(R.drawable.ic_bug_report),
            title = "Open a Github issue",
            subtitle = "Bug Report/Feature Request",
            enabled = true,
            onClick = { openUrl(context, "https://github.com/First-Non-Interesting-Username/projects-list/issues/new") },
        )
        Spacer(Modifier.height(8.dp))
        ActionRow(
            icon = painterResource(R.drawable.ic_attribution),
            title = "License attribution",
            subtitle = "",
            enabled = true,
            onClick = { navController.navigate(io.github.first_non_interesting_username.projects_list.Routes.LICENSES) }
        )
    }
}