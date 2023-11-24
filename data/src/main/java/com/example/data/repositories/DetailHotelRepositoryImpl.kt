package com.example.data.repositories

import com.example.data.base.BaseRepository
import com.example.data.remote.apiservices.DetailHotelApiService
import com.example.domain.repositories.DetailHotelRepository
import javax.inject.Inject

class DetailHotelRepositoryImpl @Inject constructor(
    private val detailHotelApiService: DetailHotelApiService
) : BaseRepository(), DetailHotelRepository {

    override fun fetchDetailHotel() = doNetworkRequestWithMapping {
        detailHotelApiService.fetchDetailINfoOfHotel()
    }
}