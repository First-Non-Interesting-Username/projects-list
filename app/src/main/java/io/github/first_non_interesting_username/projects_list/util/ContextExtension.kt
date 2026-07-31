package io.github.first_non_interesting_username.projects_list.util

import android.content.Context
import android.content.Intent
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.core.net.toUri

fun Context.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}