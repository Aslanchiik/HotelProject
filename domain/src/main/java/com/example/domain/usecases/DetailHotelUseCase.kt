package com.example.domain.usecases

import com.example.domain.repositories.DetailHotelRepository
import javax.inject.Inject

class DetailHotelUseCase @Inject constructor(
    private val detailHotelRepository: DetailHotelRepository
) {
    operator fun invoke() = detailHotelRepository.fetchDetailHotel()
}