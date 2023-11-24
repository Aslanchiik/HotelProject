package com.example.hotelproject.presentation.extensions

import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun Fragment.showToastShort(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun TextInputEditText.disableErrorWhenStartWrite(inputLayout: TextInputLayout) {
    doOnTextChanged { _, _, _, _ ->
        inputLayout.isErrorEnabled = false
    }
}

fun TextInputEditText.textWithTrim() = this.text.toString().trim()

