package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.remote.apiservices.DetailHotelApiService
import com.example.data.remote.apiservices.HotelApiService
import com.example.data.remote.apiservices.RoomApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val provideRetrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideHotelApiService(): HotelApiService = provideRetrofit.create(
        HotelApiService::class.java
    )

    fun provideRoomsApiService(): RoomApiService = provideRetrofit.create(
        RoomApiService::class.java
    )

    fun provideDetailHotelInfoApiService(): DetailHotelApiService = provideRetrofit.create(
        DetailHotelApiService::class.java
    )
}