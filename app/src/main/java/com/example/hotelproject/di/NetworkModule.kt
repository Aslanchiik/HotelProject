package com.example.hotelproject.di

import com.example.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    private val retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideHotelApiService() = retrofitClient.provideHotelApiService()

    @Singleton
    @Provides
    fun provideRoomApiService() = retrofitClient.provideRoomsApiService()

    @Singleton
    @Provides
    fun provideDetailHotelApiService() = retrofitClient.provideDetailHotelInfoApiService()
}