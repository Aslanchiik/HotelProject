package com.example.domain.usecases

import com.example.domain.repositories.RoomRepository
import javax.inject.Inject

class RoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke() = roomRepository.fetchRooms()
}