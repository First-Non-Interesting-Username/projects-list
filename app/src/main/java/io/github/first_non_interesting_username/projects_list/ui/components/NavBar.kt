package io.github.first_non_interesting_username.projects_list.ui.components

import androidx.annotation.DrawableRes
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

enum class Destination(
    val route: String,
    val label: String,
    @DrawableRes val iconRes: Int,
) {
    SEARCH(
        route = "search",
        label = "Search projects",
        iconRes = R.drawable.ic_search,
    ),
    ADD(
        route = "new_project",
        label = "New project",
        iconRes = R.drawable.ic_add,
    ),
    RANDOM(
        route = "random",
        label = "Random project",
        iconRes = R.drawable.ic_casino,
    ),
}

@Composable
fun AppBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier.Companion,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        Destination.entries.forEach { destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(destination.iconRes),
                        contentDescription = destination.label,
                    )
                },
                label = { Text(destination.label) },
            )
        }
    }
}