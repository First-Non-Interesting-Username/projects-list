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

@Composable
fun AppBottomBar(
    navController: NavHostController,

    modifier: Modifier = Modifier.Companion,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = currentRoute == "search",
            onClick = {
                navController.navigate("search") {
                    launchSingleTop = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search projects",
                )
            },
            label = { Text("Search projects") },
        )
        NavigationBarItem(
            selected = currentRoute == "new_project",
            onClick = {
                navController.navigate("new_project") {
                    launchSingleTop = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "New project",
                )
            },
            label = { Text("New project") },
        )
        NavigationBarItem(
            selected = currentRoute == "random",
            onClick = {
                navController.navigate("random") {
                    launchSingleTop = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_casino),
                    contentDescription = "Random project",
                )
            },
            label = { Text("Random project") },
        )
    }
}