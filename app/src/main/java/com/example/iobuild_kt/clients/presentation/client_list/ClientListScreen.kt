package com.example.iobuild_kt.clients.presentation.client_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.iobuild_kt.clients.domain.model.Client
import com.example.iobuild_kt.clients.presentation.client_detail.ClientDetailDialog
import com.example.iobuild_kt.clients.presentation.components.ClientCard
import com.example.iobuild_kt.clients.presentation.components.ClientFormData
import com.example.iobuild_kt.clients.presentation.components.ClientFormDialog
import com.example.iobuild_kt.core.ui.components.ErrorScreen
import com.example.iobuild_kt.core.ui.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ClientListScreen(
    viewModel: ClientListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val filtered = viewModel.getFilteredClients()

    var showCreate by remember { mutableStateOf(false) }
    var editingClient by remember { mutableStateOf<Client?>(null) }
    var viewingClient by remember { mutableStateOf<Client?>(null) }

    when (val current = state) {
        is ClientListUiState.Loading -> LoadingScreen()
        is ClientListUiState.Error -> ErrorScreen(message = current.message, onRetry = { viewModel.loadClients() })
        is ClientListUiState.Success -> {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { showCreate = true }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                    }
                }
            ) { padding ->
                Column(Modifier.fillMaxSize().padding(padding)) {
                    OutlinedTextField(
                        value = searchQuery, onValueChange = viewModel::setSearchQuery,
                        placeholder = { Text("Buscar cliente...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Close, contentDescription = null)
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    )

                    if (filtered.isEmpty()) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No se encontraron clientes", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(filtered, key = { it.id }) { client ->
                                ClientCard(client = client, onClick = { viewingClient = client })
                            }
                            item { Spacer(Modifier.height(72.dp)) }
                        }
                    }
                }
            }
        }
    }

    if (showCreate) {
        ClientFormDialog(
            title = "Nuevo Cliente", initial = ClientFormData(),
            onDismiss = { showCreate = false },
            onSave = { viewModel.createClient(it); showCreate = false }
        )
    }

    editingClient?.let { c ->
        ClientFormDialog(
            title = "Editar Cliente",
            initial = ClientFormData(
                fullName = c.fullName, projectName = c.projectName, projectId = c.projectId,
                accountStatement = c.accountStatement, email = c.email,
                phoneNumber = c.phoneNumber, address = c.address
            ),
            onDismiss = { editingClient = null },
            onSave = { viewModel.updateClient(c.id, it); editingClient = null }
        )
    }

    viewingClient?.let { c ->
        ClientDetailDialog(
            client = c, onDismiss = { viewingClient = null },
            onEdit = { editingClient = c; viewingClient = null },
            onDelete = { viewModel.deleteClient(c.id); viewingClient = null }
        )
    }
}
