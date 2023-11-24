package com.example.data.remote.apiservices

import com.example.data.dtos.DetailModelDto
import retrofit2.Response
import retrofit2.http.GET

interface DetailHotelApiService {

    @GET("v3/63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun fetchDetailINfoOfHotel(): Response<DetailModelDto>
}