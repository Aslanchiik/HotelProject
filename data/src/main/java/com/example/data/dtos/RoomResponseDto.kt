package com.example.data.dtos

import com.example.data.utils.DataMapper
import com.example.domain.models.RoomResponseModel
import com.google.gson.annotations.SerializedName

data class RoomResponseDto(
    @SerializedName("rooms")
    val rooms: List<RoomModelDto>
) : DataMapper<RoomResponseModel> {
    override fun mapToDomain() = RoomResponseModel(rooms = rooms.map { it.mapToDomain() })
}