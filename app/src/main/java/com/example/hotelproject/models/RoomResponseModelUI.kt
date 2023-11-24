package com.example.hotelproject.models

import com.example.domain.models.RoomResponseModel

data class RoomResponseModelUI(
    val rooms: List<RoomModelUI>
)

fun RoomResponseModel.toUI() = RoomResponseModelUI(
    rooms.map { it.toUI() }
)


