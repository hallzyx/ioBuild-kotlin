package com.example.iobuild_kt.clients.presentation.client_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.iobuild_kt.clients.domain.model.Client

@Composable
fun ClientDetailDialog(
    client: Client,
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val statusColor = when (client.accountStatement) {
        "Active" -> Color(0xFF10B981); "Pending" -> Color(0xFFF59E0B)
        "Inactive" -> Color(0xFF6B7280); "Suspended" -> Color(0xFFEF4444)
        else -> Color(0xFF6B7280)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(client.fullName, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row {
                    Text("Estado: ", fontWeight = FontWeight.Medium)
                    Text(client.accountStatement, color = statusColor, fontWeight = FontWeight.Bold)
                }
                InfoRow("Correo", client.email)
                InfoRow("Teléfono", client.phoneNumber)
                InfoRow("Dirección", client.address)
                InfoRow("Proyecto", client.projectName)
            }
        },
        confirmButton = {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onEdit, modifier = Modifier.weight(1f)) { Text("Editar") }
                Button(
                    onClick = onDelete, modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) { Text("Eliminar") }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cerrar") }
        }
    )
}

@Composable
private fun InfoRow(label: String, value: String) {
    if (value.isNotBlank()) {
        Row {
            Text("$label: ", fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value)
        }
    }
}
