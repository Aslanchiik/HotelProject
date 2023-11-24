package com.example.domain.repositories

import com.example.domain.models.DetailModel
import com.example.domain.utils.RemoteWrapper

interface DetailHotelRepository {

    fun fetchDetailHotel(): RemoteWrapper<DetailModel>

}