package com.example.data.repositories

import com.example.data.base.BaseRepository
import com.example.data.remote.apiservices.RoomApiService
import com.example.domain.repositories.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomApiService: RoomApiService
) : BaseRepository(), RoomRepository {

    override fun fetchRooms() = doNetworkRequestWithMapping {
        roomApiService.fetchRooms()
    }
}
