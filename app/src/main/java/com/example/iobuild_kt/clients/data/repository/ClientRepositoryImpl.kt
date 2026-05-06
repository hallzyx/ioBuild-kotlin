package com.example.iobuild_kt.clients.data.repository

import com.example.iobuild_kt.clients.data.api.ClientApiService
import com.example.iobuild_kt.clients.data.dto.toCreateRequest
import com.example.iobuild_kt.clients.data.dto.toDomain
import com.example.iobuild_kt.clients.data.dto.toUpdateRequest
import com.example.iobuild_kt.clients.domain.model.Client
import com.example.iobuild_kt.clients.domain.repository.ClientRepository

class ClientRepositoryImpl(
    private val api: ClientApiService
) : ClientRepository {

    override suspend fun getClients(): Result<List<Client>> = runCatching {
        api.getAllClients().map { it.toDomain() }
    }

    override suspend fun getClientById(id: Int): Result<Client> = runCatching {
        api.getClientById(id).toDomain()
    }

    override suspend fun createClient(client: Client): Result<Client> = runCatching {
        api.createClient(client.toCreateRequest()).toDomain()
    }

    override suspend fun updateClient(client: Client): Result<Client> = runCatching {
        api.updateClient(client.id, client.toUpdateRequest()).toDomain()
    }

    override suspend fun deleteClient(id: Int): Result<Unit> = runCatching {
        api.deleteClient(id)
    }
}
