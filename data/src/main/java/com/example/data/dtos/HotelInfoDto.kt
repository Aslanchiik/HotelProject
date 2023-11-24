package com.example.data.dtos

import com.example.data.utils.DataMapper
import com.example.domain.models.HotelInfo
import com.google.gson.annotations.SerializedName

data class HotelInfoDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>
) : DataMapper<HotelInfo> {
    override fun mapToDomain() = HotelInfo(
        description, peculiarities
    )
}
