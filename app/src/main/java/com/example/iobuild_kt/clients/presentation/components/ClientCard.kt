package com.example.iobuild_kt.clients.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.iobuild_kt.clients.domain.model.Client

@Composable
fun ClientCard(
    client: Client,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val statusColor = when (client.accountStatement) {
        "Active" -> Color(0xFF10B981)
        "Pending" -> Color(0xFFF59E0B)
        "Inactive" -> Color(0xFF6B7280)
        "Suspended" -> Color(0xFFEF4444)
        else -> Color(0xFF6B7280)
    }

    Card(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = client.fullName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = client.email,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = client.accountStatement,
                    style = MaterialTheme.typography.labelSmall,
                    color = statusColor,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = client.projectName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            if (client.phoneNumber.isNotBlank()) {
                Spacer(Modifier.height(2.dp))
                Text(
                    text = client.phoneNumber,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
