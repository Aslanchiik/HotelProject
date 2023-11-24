package com.example.hotelproject.presentation.ui.fragments.hotel

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.utils.NetworkError
import com.example.hotelproject.R
import com.example.hotelproject.databinding.FragmentHotelBinding
import com.example.hotelproject.presentation.base.BaseFragment
import com.example.hotelproject.presentation.ui.adapters.ImageCollageAdapter
import com.example.hotelproject.presentation.ui.adapters.PeculiaritiesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelFragment : BaseFragment<HotelViewModel, FragmentHotelBinding>(R.layout.fragment_hotel) {

    override val viewModel: HotelViewModel by viewModels()
    override val binding by viewBinding(FragmentHotelBinding::bind)
    private val peculiaritiesAdapter = PeculiaritiesAdapter()
    private val collageAdapter = ImageCollageAdapter()

    override fun initialize() {
        binding.particularRecView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = peculiaritiesAdapter
        }
        binding.collageImageRecView.adapter = collageAdapter
    }

    override fun setupRequests() {
        viewModel.fetchHotels()
    }

    @SuppressLint("SetTextI18n")
    override fun setupSubscribers() {
        viewModel.hotelState.collectUIState(
            onError = {
                Log.e("error", (it as NetworkError.Unexpected).error)
            },
            onSuccess = {
                binding.apply {
                    nameOfHotelTxt.text = it.name
                    locationTxt.text = it.address
                    ratingTxt.text = "${it.rating} ${it.ratingName}"
                    priceTxt.text = "от ${it.minimalPrice}₽ "
                    withTourTxt.text = it.priceForIt
                    peculiaritiesAdapter.setList(it.aboutTheHotel.peculiarities)
                    descriptiobTxt.text = it.aboutTheHotel.description
                    collageAdapter.submitList(it.imageUrls)
                }
            }
        )
    }

    override fun setupListeners() {
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_hotelFragment_to_roomFragment)
        }
    }
}