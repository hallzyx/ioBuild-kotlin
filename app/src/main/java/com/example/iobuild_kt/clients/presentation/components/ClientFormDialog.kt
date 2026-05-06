package com.example.iobuild_kt.clients.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.iobuild_kt.core.i18n.lang

data class ClientFormData(
    val fullName: String = "",
    val projectName: String = "",
    val projectId: Int = 1,
    val accountStatement: String = "Active",
    val email: String = "",
    val phoneNumber: String = "",
    val address: String = ""
)

private val statusOptions = listOf("Active", "Inactive", "Suspended", "Pending")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientFormDialog(
    title: String,
    initial: ClientFormData,
    isSaving: Boolean = false,
    error: String? = null,
    onDismiss: () -> Unit,
    onSave: (ClientFormData) -> Unit
) {
    var fullName by remember { mutableStateOf(initial.fullName) }
    var projectName by remember { mutableStateOf(initial.projectName) }
    var email by remember { mutableStateOf(initial.email) }
    var phone by remember { mutableStateOf(initial.phoneNumber) }
    var address by remember { mutableStateOf(initial.address) }
    var statusExpanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf(initial.accountStatement) }

    AlertDialog(
        onDismissRequest = { if (!isSaving) onDismiss() },
        title = { Text(title, fontWeight = FontWeight.Bold) },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text(lang("clients.name")) }, singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
                OutlinedTextField(value = projectName, onValueChange = { projectName = it }, label = { Text(lang("clients.project")) }, singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text(lang("clients.email")) }, singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
                OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text(lang("clients.phone")) }, singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
                OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text(lang("clients.address")) }, singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
                ExposedDropdownMenuBox(expanded = statusExpanded, onExpandedChange = { statusExpanded = it }) {
                    OutlinedTextField(
                        value = selectedStatus,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(lang("clients.status")) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = statusExpanded) },
                        modifier = Modifier.menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    ExposedDropdownMenu(expanded = statusExpanded, onDismissRequest = { statusExpanded = false }) {
                        statusOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = { selectedStatus = option; statusExpanded = false }
                            )
                        }
                    }
                }

                if (error != null) {
                    Text(error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(ClientFormData(
                        fullName = fullName, projectName = projectName, projectId = initial.projectId,
                        accountStatement = selectedStatus, email = email, phoneNumber = phone, address = address
                    ))
                },
                enabled = !isSaving && fullName.isNotBlank()
            ) {
                if (isSaving) CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                else Text(lang("clients.save"))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, enabled = !isSaving) { Text(lang("clients.cancel")) }
        }
    )
}
