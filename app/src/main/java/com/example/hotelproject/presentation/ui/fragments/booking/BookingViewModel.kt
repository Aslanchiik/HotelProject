package com.example.hotelproject.presentation.ui.fragments.booking

import com.example.domain.usecases.DetailHotelUseCase
import com.example.hotelproject.models.DetailModelUI
import com.example.hotelproject.models.toUI
import com.example.hotelproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val detailHotelUseCase: DetailHotelUseCase
) : BaseViewModel() {

    private val _detailHotelState = MutableUIStateFlow<DetailModelUI>()
    val detailHotelState = _detailHotelState.asStateFlow()

    fun fetchDetailHotel() {
        detailHotelUseCase().collectRequest(_detailHotelState) {
            it.toUI()
        }
    }
}