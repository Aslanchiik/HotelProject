package com.example.hotelproject.presentation.ui.customviews

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class InputEditPhoneNumber : TextInputEditText {

    private lateinit var phoneNumberFormatWatcher: FormatWatcher

    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        setup()
    }

    private fun setup() {
        setText("+7")
        inputType = InputType.TYPE_CLASS_PHONE

        val slots = PhoneNumberUnderscoreSlotsParser().parseSlots("+7(___) ___-__-__")
        val inputMask = MaskImpl.createTerminated(slots).apply {
            isHideHardcodedHead = true
        }
        phoneNumberFormatWatcher = MaskFormatWatcher(inputMask)
        phoneNumberFormatWatcher.installOn(this)
    }

    fun getRawPhoneNumber() = phoneNumberFormatWatcher.mask.toUnformattedString().replace("+", "")
}