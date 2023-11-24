package com.example.hotelproject.models

import com.example.domain.models.HotelModel
import com.example.hotelproject.presentation.base.IBaseDiffModel

data class HotelModelUI(
    override val id: Int,
    val name: String,
    val address: String,
    val minimalPrice: Int,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String,
    val imageUrls: List<String>,
    val aboutTheHotel: HotelInfoUI
) : IBaseDiffModel<Int>

fun HotelModel.toUI() = HotelModelUI(
    id, name, address, minimalPrice, priceForIt, rating, ratingName, imageUrls, aboutTheHotel.toUI()
)
