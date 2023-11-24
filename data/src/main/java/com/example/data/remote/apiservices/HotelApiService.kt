package com.example.data.remote.apiservices

import com.example.data.dtos.HotelModelDto
import retrofit2.Response
import retrofit2.http.GET

interface HotelApiService {

    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun fetchHotels(): Response<HotelModelDto>
}