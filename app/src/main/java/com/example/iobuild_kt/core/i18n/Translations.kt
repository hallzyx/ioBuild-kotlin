package com.example.iobuild_kt.core.i18n

object Translations {

    private val es: Map<String, String> = mapOf(
        // Header
        "header.welcome" to "Bienvenido",

        // Nav
        "nav.home" to "Inicio",
        "nav.profile" to "Perfil",
        "nav.projects" to "Proyectos",
        "nav.clients" to "Clientes",
        "nav.devices" to "Dispositivos",
        "nav.subscription" to "Suscripción",
        "nav.configuration" to "Configuración",
        "nav.logout" to "Cerrar Sesión",
        "nav.language" to "Idioma",
        "nav.spanish" to "Español",
        "nav.english" to "English",

        // Role
        "role.builder" to "Constructor",
        "role.owner" to "Propietario",

        // Auth / Login
        "login.title" to "IoBuild",
        "login.subtitle" to "Panel del Constructor",
        "login.email" to "Correo Electrónico",
        "login.email_placeholder" to "Ingrese su correo",
        "login.password" to "Contraseña",
        "login.password_placeholder" to "Ingrese su contraseña",
        "login.submit" to "Iniciar Sesión",
        "login.no_account" to "¿No tienes cuenta? Regístrate",
        "login.show_password" to "Mostrar contraseña",
        "login.hide_password" to "Ocultar contraseña",
        "login.error_required" to "Completa todos los campos",
        "login.error_invalid" to "Credenciales incorrectas. Verifica tu email y contraseña.",
        "login.error_server" to "Error del servidor. Intenta de nuevo más tarde.",
        "login.error_network" to "Error de conexión. Verifica tu internet.",
        "login.error_unknown" to "Error inesperado. Intenta de nuevo.",

        // Dashboard
        "dashboard.title" to "Panel del Constructor",
        "dashboard.loading" to "Cargando dashboard...",
        "dashboard.error" to "Error al cargar el dashboard. Intenta de nuevo.",
        "dashboard.retry" to "Reintentar",
        "dashboard.stats.active_projects" to "Proyectos Activos",
        "dashboard.stats.total" to "en total",
        "dashboard.stats.devices" to "Dispositivos",
        "dashboard.stats.connected" to "conectados",
        "dashboard.stats.occupied_units" to "Unidades Ocupadas",
        "dashboard.stats.occupancy" to "ocupación",
        "dashboard.stats.energy" to "Eficiencia Energética",
        "dashboard.stats.avg" to "promedio general",
        "dashboard.chart.device_distribution" to "Distribución de Dispositivos",
        "dashboard.chart.occupancy" to "Tasa de Ocupación Mensual",
        "dashboard.chart.energy" to "Consumo Energético (kWh)",
        "dashboard.chart.energy_subtitle" to "Últimos 30 días",
        "dashboard.chart.no_data" to "No hay datos disponibles",
        "dashboard.section.projects" to "Resumen de Proyectos",

        // Project status
        "project.status.on_going" to "En Curso",
        "project.status.completed" to "Completado",
        "project.status.planned" to "Planeado",
        "project.status.on_hold" to "En Pausa",
        "project.status.cancelled" to "Cancelado",
        "project.units" to "unidades",
        "project.occupancy" to "ocupación",
        "project.devices" to "dispositivos",

        // General
        "general.loading" to "Cargando...",
        "general.error" to "Ocurrió un error",
    )

    private val en: Map<String, String> = mapOf(
        "header.welcome" to "Welcome",
        "nav.home" to "Home",
        "nav.profile" to "Profile",
        "nav.projects" to "Projects",
        "nav.clients" to "Clients",
        "nav.devices" to "Devices",
        "nav.subscription" to "Subscription",
        "nav.configuration" to "Settings",
        "nav.logout" to "Log Out",
        "nav.language" to "Language",
        "nav.spanish" to "Español",
        "nav.english" to "English",
        "role.builder" to "Builder",
        "role.owner" to "Owner",
        "login.title" to "IoBuild",
        "login.subtitle" to "Builder Panel",
        "login.email" to "Email",
        "login.email_placeholder" to "Enter your email",
        "login.password" to "Password",
        "login.password_placeholder" to "Enter your password",
        "login.submit" to "Sign In",
        "login.no_account" to "Don't have an account? Register",
        "login.show_password" to "Show password",
        "login.hide_password" to "Hide password",
        "login.error_required" to "Fill in all fields",
        "login.error_invalid" to "Invalid credentials. Check your email and password.",
        "login.error_server" to "Server error. Try again later.",
        "login.error_network" to "Connection error. Check your internet.",
        "login.error_unknown" to "Unexpected error. Try again.",
        "dashboard.title" to "Builder Dashboard",
        "dashboard.loading" to "Loading dashboard...",
        "dashboard.error" to "Failed to load dashboard. Try again.",
        "dashboard.retry" to "Retry",
        "dashboard.stats.active_projects" to "Active Projects",
        "dashboard.stats.total" to "total",
        "dashboard.stats.devices" to "Devices",
        "dashboard.stats.connected" to "connected",
        "dashboard.stats.occupied_units" to "Occupied Units",
        "dashboard.stats.occupancy" to "occupancy",
        "dashboard.stats.energy" to "Energy Efficiency",
        "dashboard.stats.avg" to "overall average",
        "dashboard.chart.device_distribution" to "Device Distribution",
        "dashboard.chart.occupancy" to "Monthly Occupancy Rate",
        "dashboard.chart.energy" to "Energy Consumption (kWh)",
        "dashboard.chart.energy_subtitle" to "Last 30 days",
        "dashboard.chart.no_data" to "No data available",
        "dashboard.section.projects" to "Project Overview",
        "project.status.on_going" to "In Progress",
        "project.status.completed" to "Completed",
        "project.status.planned" to "Planned",
        "project.status.on_hold" to "On Hold",
        "project.status.cancelled" to "Cancelled",
        "project.units" to "units",
        "project.occupancy" to "occupancy",
        "project.devices" to "devices",
        "general.loading" to "Loading...",
        "general.error" to "An error occurred",
    )

    private val allLanguages = mapOf(
        "es" to es,
        "en" to en
    )

    fun get(key: String, lang: String = "es"): String {
        return allLanguages[lang]?.get(key)
            ?: allLanguages["es"]?.get(key)
            ?: key
    }

    fun getAvailableLanguages(): List<Pair<String, String>> = listOf(
        "es" to "Español",
        "en" to "English"
    )
}
