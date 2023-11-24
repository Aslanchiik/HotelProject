package com.example.domain.usecases

import com.example.domain.repositories.HotelRepository
import javax.inject.Inject

class HotelUseCase @Inject constructor(
    private val repository: HotelRepository
) {
    operator fun invoke() = repository.fetchHotels()
}