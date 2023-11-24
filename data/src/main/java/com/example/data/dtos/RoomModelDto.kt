package com.example.data.dtos

import com.example.data.utils.DataMapper
import com.example.domain.models.RoomModel
import com.google.gson.annotations.SerializedName

data class RoomModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("price_per")
    val pricePer: String
) : DataMapper<RoomModel> {
    override fun mapToDomain() = RoomModel(
        id, imageUrls, name, peculiarities, price, pricePer
    )
}