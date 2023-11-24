package com.example.hotelproject.presentation.ui.fragments.room

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.utils.NetworkError
import com.example.hotelproject.R
import com.example.hotelproject.databinding.FragmentRoomBinding
import com.example.hotelproject.presentation.base.BaseFragment
import com.example.hotelproject.presentation.ui.adapters.RoomAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : BaseFragment<RoomViewModel, FragmentRoomBinding>(R.layout.fragment_room) {

    override val binding by viewBinding(FragmentRoomBinding::bind)
    override val viewModel: RoomViewModel by viewModels()
    private val roomAdapter = RoomAdapter {
        findNavController().navigate(R.id.action_roomFragment_to_bookingFragment)
    }

    override fun initialize() {
        binding.roomRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = roomAdapter
        }
    }

    override fun setupRequests() {
        viewModel.fetchHotels()
    }

    override fun setupSubscribers() {
        viewModel.roomState.collectUIState(
            onError = {
                Log.e("error", (it as NetworkError.Unexpected).error)
            },
            onSuccess = {
                roomAdapter.submitList(it)
            }
        )
    }

    override fun setupListeners() {
        binding.arrowBackImg.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}