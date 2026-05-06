package com.example.iobuild_kt.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.iobuild_kt.auth.presentation.LoginScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Dashboard.route) {
            // TODO: DashboardScreen
            PlaceholderScreen("Dashboard - Panel del Constructor")
        }

        composable(Screen.ProjectList.route) {
            // TODO: ProjectListScreen
            PlaceholderScreen("Proyectos")
        }

        composable(
            route = Screen.ProjectDetail.route,
            arguments = listOf(navArgument("projectId") { type = NavType.IntType })
        ) {
            // TODO: ProjectDetailScreen
            PlaceholderScreen("Detalle del Proyecto")
        }

        composable(
            route = Screen.ProjectForm.route,
            arguments = listOf(
                navArgument("projectId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            // TODO: ProjectFormScreen
            PlaceholderScreen("Formulario Proyecto")
        }

        composable(Screen.ClientList.route) {
            // TODO: ClientListScreen
            PlaceholderScreen("Clientes")
        }

        composable(
            route = Screen.ClientDetail.route,
            arguments = listOf(navArgument("clientId") { type = NavType.IntType })
        ) {
            // TODO: ClientDetailScreen
            PlaceholderScreen("Detalle del Cliente")
        }

        composable(Screen.DeviceList.route) {
            // TODO: DeviceListScreen
            PlaceholderScreen("Dispositivos")
        }

        composable(Screen.Subscription.route) {
            // TODO: SubscriptionScreen
            PlaceholderScreen("Suscripción")
        }

        composable(Screen.Profile.route) {
            // TODO: ProfileScreen
            PlaceholderScreen("Perfil")
        }

        composable(Screen.Settings.route) {
            // TODO: SettingsScreen
            PlaceholderScreen("Configuración")
        }
    }
}
