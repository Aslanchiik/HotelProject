package com.example.hotelproject.presentation.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.hotelproject.R
import com.example.hotelproject.databinding.ItemAddTouristBinding
import com.example.hotelproject.presentation.utils.TransferTourist
import com.google.android.material.textfield.TextInputLayout

class EnhancedTouristInfoView(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {

    val binding: ItemAddTouristBinding =
        ItemAddTouristBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setupHideShowButton()
        setupEditTextFocusListeners()
    }

    private fun setupHideShowButton() {
        binding.imgArrowShowHide.setOnClickListener {
            toggleTouristInputVisibility()
        }
    }

    private fun setupEditTextFocusListeners() {
        for (index in 0 until binding.layoutFields.childCount) {
            val view = binding.layoutFields.getChildAt(index) as? TextInputLayout
            view?.editText?.setOnFocusChangeListener { _, hasFocus ->
                handleEditTextFocusChange(view, hasFocus)
            }
        }
    }

    private fun toggleTouristInputVisibility() {
        val newVisibility =
            if (binding.layoutFields.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        binding.layoutFields.visibility = newVisibility

        val arrowDrawable = AppCompatResources.getDrawable(
            context,
            if (newVisibility == View.VISIBLE) R.drawable.arrow_up else R.drawable.arrow_down
        )
        binding.imgArrowShowHide.background = arrowDrawable
    }

    private fun handleEditTextFocusChange(view: TextInputLayout?, hasFocus: Boolean) {
        view?.editText?.backgroundTintList = if (hasFocus) ContextCompat.getColorStateList(
            context, R.color.gray
        ) else null
    }

    fun setTouristNumber(number: Int) {
        binding.numberOfTourist.text = "${TransferTourist.getNumber(number)} турист"
    }

    fun checkEmptyField(): Boolean {
        var allFieldsFilled = true
        for (index in 0 until binding.layoutFields.childCount) {
            val view = binding.layoutFields.getChildAt(index) as? TextInputLayout
            if (view != null && view.editText?.text?.isBlank() == true) {
                view.editText?.error = context.getString(R.string.fill_the_fields)
                allFieldsFilled = false
            }
        }
        return allFieldsFilled
    }
}
