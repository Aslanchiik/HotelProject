package com.example.data.remote.apiservices

import com.example.data.dtos.RoomResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface RoomApiService {

    @GET("v3/8b532701-709e-4194-a41c-1a903af00195")
    suspend fun fetchRooms(): Response<RoomResponseDto>
}