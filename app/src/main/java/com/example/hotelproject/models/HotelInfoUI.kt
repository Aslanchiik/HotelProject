package com.example.hotelproject.models

import com.example.domain.models.HotelInfo

data class HotelInfoUI(
    val description: String,
    val peculiarities: ArrayList<String>
)

fun HotelInfo.toUI() = HotelInfoUI(
    description, peculiarities
)
