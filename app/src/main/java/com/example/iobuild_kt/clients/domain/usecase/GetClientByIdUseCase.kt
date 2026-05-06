package com.example.iobuild_kt.clients.domain.usecase

import com.example.iobuild_kt.clients.domain.repository.ClientRepository

class GetClientByIdUseCase(private val repository: ClientRepository) {
    suspend operator fun invoke(id: Int) = repository.getClientById(id)
}
