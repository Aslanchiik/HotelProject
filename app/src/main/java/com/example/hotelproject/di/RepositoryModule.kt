package com.example.hotelproject.di

import com.example.data.repositories.DetailHotelRepositoryImpl
import com.example.data.repositories.HotelRepositoryImpl
import com.example.data.repositories.RoomRepositoryImpl
import com.example.domain.repositories.DetailHotelRepository
import com.example.domain.repositories.HotelRepository
import com.example.domain.repositories.RoomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHotelRepository(
        repositoryImpl: HotelRepositoryImpl
    ): HotelRepository

    @Binds
    abstract fun bindRoomRepository(
        roomRepositoryImpl: RoomRepositoryImpl
    ): RoomRepository


    @Binds
    abstract fun bindDetailHotelRepository(
        roomRepositoryImpl: DetailHotelRepositoryImpl
    ): DetailHotelRepository
}