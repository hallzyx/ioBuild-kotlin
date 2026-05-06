package com.example.iobuild_kt.clients.presentation.client_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iobuild_kt.clients.domain.model.Client
import com.example.iobuild_kt.clients.domain.usecase.CreateClientUseCase
import com.example.iobuild_kt.clients.domain.usecase.DeleteClientUseCase
import com.example.iobuild_kt.clients.domain.usecase.GetClientsUseCase
import com.example.iobuild_kt.clients.domain.usecase.UpdateClientUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ClientListUiState {
    data object Loading : ClientListUiState()
    data class Success(val clients: List<Client>) : ClientListUiState()
    data class Error(val message: String) : ClientListUiState()
}

class ClientListViewModel(
    private val getClients: GetClientsUseCase,
    private val createClient: CreateClientUseCase,
    private val updateClient: UpdateClientUseCase,
    private val deleteClient: DeleteClientUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ClientListUiState>(ClientListUiState.Loading)
    val state: StateFlow<ClientListUiState> = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init { loadClients() }

    fun loadClients() {
        viewModelScope.launch {
            _state.value = ClientListUiState.Loading
            getClients().let { result ->
                _state.value = if (result.isSuccess) {
                    ClientListUiState.Success(result.getOrDefault(emptyList()))
                } else {
                    ClientListUiState.Error(result.exceptionOrNull()?.message ?: "Error al cargar clientes")
                }
            }
        }
    }

    fun setSearchQuery(query: String) { _searchQuery.value = query }

    fun createClient(data: com.example.iobuild_kt.clients.presentation.components.ClientFormData) {
        viewModelScope.launch {
            createClient(Client(
                fullName = data.fullName, projectName = data.projectName,
                projectId = data.projectId, accountStatement = data.accountStatement,
                email = data.email, phoneNumber = data.phoneNumber, address = data.address
            ))
            loadClients()
        }
    }

    fun updateClient(id: Int, data: com.example.iobuild_kt.clients.presentation.components.ClientFormData) {
        viewModelScope.launch {
            updateClient(Client(
                id = id, fullName = data.fullName, projectName = data.projectName,
                projectId = data.projectId, accountStatement = data.accountStatement,
                email = data.email, phoneNumber = data.phoneNumber, address = data.address
            ))
            loadClients()
        }
    }

    fun deleteClient(id: Int) {
        viewModelScope.launch { deleteClient(id); loadClients() }
    }

    fun getFilteredClients(): List<Client> {
        val current = _state.value
        if (current !is ClientListUiState.Success) return emptyList()
        val q = _searchQuery.value.lowercase().trim()
        if (q.isEmpty()) return current.clients
        return current.clients.filter {
            it.fullName.lowercase().contains(q) || it.email.lowercase().contains(q) || it.projectName.lowercase().contains(q)
        }
    }
}
