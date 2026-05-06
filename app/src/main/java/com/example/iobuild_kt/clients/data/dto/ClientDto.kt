package com.example.iobuild_kt.clients.data.dto

data class ClientDto(
    val id: Int,
    val fullName: String,
    val projectId: Int,
    val projectName: String,
    val accountStatement: String,
    val email: String,
    val phoneNumber: String,
    val address: String
)
