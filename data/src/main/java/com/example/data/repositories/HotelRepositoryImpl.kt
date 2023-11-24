package com.example.data.repositories

import com.example.data.base.BaseRepository
import com.example.data.remote.apiservices.HotelApiService
import com.example.domain.repositories.HotelRepository
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val service: HotelApiService
) : BaseRepository(), HotelRepository {

    override fun fetchHotels() = doNetworkRequestWithMapping {
        service.fetchHotels()
    }
}