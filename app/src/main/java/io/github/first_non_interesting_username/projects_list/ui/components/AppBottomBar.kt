package io.github.first_non_interesting_username.projects_list.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.github.first_non_interesting_username.projects_list.R
import java.security.KeyStore

@Composable
fun AppBottomBar(
    navController: NavHostController,
    content: String,
    onRandomClick: () -> Unit,
    modifier: Modifier = Modifier.Companion,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = currentRoute == "search",
            onClick = {
                navController.navigate("search")
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search ${content}s",
                )
            },
            label = { Text("Search ${content}s") },
        )
        NavigationBarItem(
            selected = currentRoute == "new_${content}",
            onClick = {
                navController.navigate("new_${content}")
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "New ${content}",
                )
            },
            label = { Text("New ${content}") },
        )
        NavigationBarItem(
            // I'm a genius
            selected = false,
            onClick = {
                onRandomClick()
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_casino),
                    contentDescription = "Random ${content}",
                )
            },
            label = { Text("Random ${content}") },
        )
    }
}