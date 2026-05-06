package com.example.iobuild_kt.settings.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.iobuild_kt.core.i18n.lang

@Composable
fun ChangePasswordDialog(
    onDismiss: () -> Unit,
    onSave: (current: String, new: String) -> Unit
) {
    var current by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(lang("settings.change_password"), fontWeight = FontWeight.Bold) },
        text = {
            Column {
                OutlinedTextField(value = current, onValueChange = { current = it; error = null }, label = { Text("Contraseña actual") }, visualTransformation = PasswordVisualTransformation(), singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = newPassword, onValueChange = { newPassword = it; error = null }, label = { Text("Nueva contraseña") }, visualTransformation = PasswordVisualTransformation(), singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = confirm, onValueChange = { confirm = it; error = null }, label = { Text("Confirmar nueva contraseña") }, visualTransformation = PasswordVisualTransformation(), singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
                if (error != null) { Spacer(Modifier.height(8.dp)); Text(error!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }
            }
        },
        confirmButton = {
            Button(onClick = {
                when {
                    current.isBlank() || newPassword.isBlank() -> error = "Completa todos los campos"
                    newPassword != confirm -> error = "Las contraseñas no coinciden"
                    newPassword.length < 6 -> error = "La contraseña debe tener al menos 6 caracteres"
                    else -> onSave(current, newPassword)
                }
            }) { Text("Guardar") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}
