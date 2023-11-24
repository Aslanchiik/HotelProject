package com.example.hotelproject.presentation.ui.fragments.booking

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.utils.NetworkError
import com.example.hotelproject.R
import com.example.hotelproject.databinding.FragmentBookingBinding
import com.example.hotelproject.presentation.base.BaseFragment
import com.example.hotelproject.presentation.extensions.disableErrorWhenStartWrite
import com.example.hotelproject.presentation.extensions.textWithTrim
import com.example.hotelproject.presentation.ui.customviews.EnhancedTouristInfoView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingFragment :
    BaseFragment<BookingViewModel, FragmentBookingBinding>(R.layout.fragment_booking) {

    override val binding by viewBinding(FragmentBookingBinding::bind)
    override val viewModel: BookingViewModel by viewModels()
    private var touristNumber = 1

    override fun setupRequests() {
        viewModel.fetchDetailHotel()
    }

    @SuppressLint("SetTextI18n")
    override fun setupSubscribers() = with(binding) {
        viewModel.detailHotelState.collectUIState(
            onError = {
                Log.e("error", (it as NetworkError.Unexpected).error)
            },
            onSuccess = {
                ratingTxt.text = "${it.horating} ${it.ratingName}"
                nameTxt.text = it.hotelName
                hotelAddressTxt.text = it.hotelAdress
                departureTxt.text = it.departure
                arrivalCountryTxt.text = it.arrivalCountry
                tourDateStartTxt.text = "${it.tourDateStart} - ${it.tourDateStop}"
                numberOfNightsTxt.text = "${it.numberOfNights} ночей"
                roomTxt.text = it.room
                nutritionTxt.text = it.nutrition
                tourPriceTxt.text = it.tourPrice.toString()
                fluerPriceTxt.text = it.fuelCharge.toString()
                servicePriceTxt.text = it.serviceCharge.toString()
                val sumPrice = it.tourPrice + it.fuelCharge + it.serviceCharge
                generalPrice.text = sumPrice.toString()
                paidBtn.text = "Оплатить $sumPrice₽"

            }
        )
    }

    override fun setupListeners() {
        setupAddTouristClickListener()
        setupDisableErrorWhenStartWrite()
        checkFillingOfContainerEDitText()
        clickPayButton()
        clickBack()
    }

    private fun setupAddTouristClickListener() = with(binding) {
        cardAddTourist.setOnClickListener {
            val newTourist = EnhancedTouristInfoView(requireContext(), null)
            newTourist.setTouristNumber(touristNumber)
            touristLinear.addView(newTourist)
            touristNumber++
        }
    }

    private fun setupDisableErrorWhenStartWrite() = with(binding) {
        inputEditPhoneNumber.disableErrorWhenStartWrite(inputLayoutPhoneNumber)
        inputEditGmail.disableErrorWhenStartWrite(inputLayoutGmail)
    }

    private fun checkPhoneNumberAndGmail() = with(binding) {
        if (inputEditPhoneNumber.getRawPhoneNumber()
                .count() != 11 || inputEditPhoneNumber.textWithTrim().isEmpty()
        ) {
            inputLayoutPhoneNumber.isErrorEnabled = true
            inputLayoutPhoneNumber.error = getString(R.string.field_is_required)
        }

        if (isValidEmail(inputEditGmail.textWithTrim())) {
            inputLayoutGmail.error = null
        } else {
            inputLayoutGmail.error = getString(R.string.enter_true_address)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches() && email.endsWith("@gmail.com", ignoreCase = true)
    }

    private fun checkFillingOfContainerEDitText(): Boolean {
        binding.apply {
            val isTouristsInfoFilled = (0 until touristLinear.childCount).all {
                val touristInfoView =
                    touristLinear.getChildAt(it) as EnhancedTouristInfoView
                touristInfoView.checkEmptyField()
            }
            return isTouristsInfoFilled
        }
    }

    private fun clickPayButton() {
        binding.paidBtn.setOnClickListener {
            checkPhoneNumberAndGmail()
            if (checkFillingOfContainerEDitText()) {
                findNavController().navigate(R.id.action_bookingFragment_to_paidFragment)
            }
        }
    }

    private fun clickBack() {
        binding.arrowBackImg.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}