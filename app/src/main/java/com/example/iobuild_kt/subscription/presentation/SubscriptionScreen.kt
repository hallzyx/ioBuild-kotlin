package com.example.iobuild_kt.subscription.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.iobuild_kt.core.ui.components.LoadingScreen
import com.example.iobuild_kt.core.ui.components.ErrorScreen
import com.example.iobuild_kt.subscription.presentation.components.PlanCard
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Scaffold

@Composable
fun SubscriptionScreen(
    viewModel: SubscriptionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val comingSoon: () -> Unit = {
        scope.launch { snackbar.showSnackbar("Esta característica estará disponible muy pronto") }
        Unit
    }

    when {
        state.isLoading -> LoadingScreen()
        state.error != null -> ErrorScreen(message = state.error!!, onRetry = { viewModel.loadData() })
        else -> {
            Scaffold(snackbarHost = { SnackbarHost(snackbar) }) { padding ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Title
                    item {
                        Text("Mi Suscripción", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    }

                    // Current subscription
                    item {
                        val sub = state.subscription
                        val plan = sub?.plan

                        if (sub != null && plan != null) {
                            CurrentPlanCard(
                                plan = plan,
                                status = sub.status,
                                startDate = sub.startDate,
                                endDate = sub.endDate,
                                onCancel = comingSoon,
                                onChangePlan = comingSoon,
                                isComingSoon = true
                            )
                        } else {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(Modifier.padding(16.dp)) {
                                    Text("No tienes una suscripción activa", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Selecciona un plan de los disponibles para empezar.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }

                    // Available plans
                    item {
                        Text("Planes Disponibles", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    }

                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(state.plans, key = { it.id }) { plan ->
                                val isCurrent = state.subscription?.plan?.id == plan.id
                                PlanCard(
                                    plan = plan,
                                    isCurrent = isCurrent,
                                    modifier = Modifier.width(280.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CurrentPlanCard(
    plan: com.example.iobuild_kt.subscription.domain.model.Plan,
    status: String,
    startDate: String,
    endDate: String,
    onCancel: () -> Unit,
    onChangePlan: () -> Unit,
    isComingSoon: Boolean
) {
    val statusColor = when (status) {
        "active" -> Color(0xFF10B981); "pending" -> Color(0xFFF59E0B)
        "cancelled" -> Color(0xFFEF4444); "expired" -> Color(0xFF6B7280)
        else -> Color(0xFF6B7280)
    }
    val statusText = when (status) {
        "active" -> "Activo"; "pending" -> "Pendiente"
        "cancelled" -> "Cancelado"; "expired" -> "Expirado"
        else -> status
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Plan Actual", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(4.dp))
            Text(plan.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text("S/ ${"%.0f".format(plan.price)} / mes", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(8.dp))
            Text(statusText, color = statusColor, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
            if (startDate.isNotBlank() && endDate.isNotBlank()) {
                Text("$startDate — $endDate", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onChangePlan, modifier = Modifier.weight(1f)) { Text("Cambiar Plan") }
                Button(onClick = onCancel, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) { Text("Cancelar") }
            }
        }
    }
}
