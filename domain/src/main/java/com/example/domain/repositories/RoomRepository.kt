package com.example.domain.repositories

import com.example.domain.models.RoomResponseModel
import com.example.domain.utils.RemoteWrapper

interface RoomRepository {

    fun fetchRooms(): RemoteWrapper<RoomResponseModel>

}