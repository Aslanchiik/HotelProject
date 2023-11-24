package com.example.hotelproject.models

import com.example.domain.models.RoomModel
import com.example.hotelproject.presentation.base.IBaseDiffModel

data class RoomModelUI(
    override val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
) : IBaseDiffModel<Int>

fun RoomModel.toUI() = RoomModelUI(
    id, imageUrls, name, peculiarities, price, pricePer
)
