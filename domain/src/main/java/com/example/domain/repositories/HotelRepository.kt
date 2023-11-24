package com.example.domain.repositories

import com.example.domain.models.HotelModel
import com.example.domain.utils.RemoteWrapper

interface HotelRepository {

    fun fetchHotels(): RemoteWrapper<HotelModel>
}