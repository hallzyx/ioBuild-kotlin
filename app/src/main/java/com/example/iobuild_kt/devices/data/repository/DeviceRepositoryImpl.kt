package com.example.iobuild_kt.devices.data.repository

import com.example.iobuild_kt.devices.data.api.DeviceApiService
import com.example.iobuild_kt.devices.data.dto.toCreateRequest
import com.example.iobuild_kt.devices.data.dto.toDomain
import com.example.iobuild_kt.devices.data.dto.toUpdateRequest
import com.example.iobuild_kt.devices.domain.model.Device
import com.example.iobuild_kt.devices.domain.repository.DeviceRepository

class DeviceRepositoryImpl(private val api: DeviceApiService) : DeviceRepository {

    override suspend fun getDevices(): Result<List<Device>> = runCatching {
        api.getAllDevices().map { it.toDomain() }
    }
    override suspend fun getDeviceById(id: Int): Result<Device> = runCatching {
        api.getDeviceById(id).toDomain()
    }
    override suspend fun createDevice(device: Device): Result<Device> = runCatching {
        api.createDevice(device.toCreateRequest()).toDomain()
    }
    override suspend fun updateDevice(device: Device): Result<Device> = runCatching {
        api.updateDevice(device.id, device.toUpdateRequest()).toDomain()
    }
    override suspend fun deleteDevice(id: Int): Result<Unit> = runCatching {
        api.deleteDevice(id)
    }
}
