package io.github.first_non_interesting_username.projects_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.first_non_interesting_username.projects_list.ui.screens.AboutScreen
import io.github.first_non_interesting_username.projects_list.ui.screens.HomeScreen
import io.github.first_non_interesting_username.projects_list.ui.screens.LicensesScreen
import io.github.first_non_interesting_username.projects_list.ui.screens.NewProjectScreen
import io.github.first_non_interesting_username.projects_list.ui.screens.ProjectScreen
import io.github.first_non_interesting_username.projects_list.ui.screens.SettingsScreen
import io.github.first_non_interesting_username.projects_list.ui.theme.ProjectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectsTheme {
                AppNavigation()
            }
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
        ) {
            composable(Routes.HOME) {
                HomeScreen(navController)
            }
            composable(Routes.SETTINGS) {
                SettingsScreen(navController)
            }
            composable(Routes.ABOUT) {
                AboutScreen(navController)
            }
            composable(Routes.LICENSES) {
                LicensesScreen(navController)
            }
            composable(Routes.NEW_PROJECT) {
                NewProjectScreen(navController)
            }
            composable(Routes.PROJECT) {
                ProjectScreen(navController)
            }
        }
    }

}
