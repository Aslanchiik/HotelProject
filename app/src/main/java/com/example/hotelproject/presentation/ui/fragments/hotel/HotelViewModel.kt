package com.example.hotelproject.presentation.ui.fragments.hotel

import com.example.domain.usecases.HotelUseCase
import com.example.hotelproject.models.HotelModelUI
import com.example.hotelproject.models.toUI
import com.example.hotelproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val hotelUseCase: HotelUseCase
) : BaseViewModel() {

    private val _hotelState = MutableUIStateFlow<HotelModelUI>()
    val hotelState = _hotelState.asStateFlow()

    fun fetchHotels() {
        hotelUseCase().collectRequest(_hotelState) {
            it.toUI()
        }
    }
}