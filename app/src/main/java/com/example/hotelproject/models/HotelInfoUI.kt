package com.example.hotelproject.models

import com.example.domain.models.HotelInfo

data class HotelInfoUI(
    val description: String,
    val peculiarities: List<String>
)

fun HotelInfo.toUI() = HotelInfoUI(
    description, peculiarities
)
