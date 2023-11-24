package com.example.hotelproject.presentation.ui.fragments.room

import com.example.domain.usecases.RoomUseCase
import com.example.hotelproject.models.RoomModelUI
import com.example.hotelproject.models.toUI
import com.example.hotelproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomUseCase: RoomUseCase
) : BaseViewModel() {

    private val _roomState = MutableUIStateFlow<List<RoomModelUI>>()
    val roomState = _roomState.asStateFlow()

    fun fetchHotels() {
        roomUseCase().collectRequest(_roomState) {
            it.rooms.map { it.toUI() }
        }
    }
}