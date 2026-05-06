package com.example.iobuild_kt.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.iobuild_kt.auth.presentation.LoginScreen
import com.example.iobuild_kt.core.i18n.LocalLanguage
import com.example.iobuild_kt.dashboard.presentation.DashboardScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    currentLang: String,
    onLanguageChange: (String) -> Unit
) {
    CompositionLocalProvider(LocalLanguage provides currentLang) {
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
                DashboardScreen()
            }

            composable(Screen.ProjectList.route) {
                PlaceholderScreen("Proyectos")
            }

            composable(
                route = Screen.ProjectDetail.route,
                arguments = listOf(navArgument("projectId") { type = NavType.IntType })
            ) {
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
                PlaceholderScreen("Formulario Proyecto")
            }

            composable(Screen.ClientList.route) {
                PlaceholderScreen("Clientes")
            }

            composable(
                route = Screen.ClientDetail.route,
                arguments = listOf(navArgument("clientId") { type = NavType.IntType })
            ) {
                PlaceholderScreen("Detalle del Cliente")
            }

            composable(Screen.DeviceList.route) {
                PlaceholderScreen("Dispositivos")
            }

            composable(Screen.Subscription.route) {
                PlaceholderScreen("Suscripción")
            }

            composable(Screen.Profile.route) {
                PlaceholderScreen("Perfil")
            }

            composable(Screen.Settings.route) {
                PlaceholderScreen("Configuración")
            }
        }
    }
}
