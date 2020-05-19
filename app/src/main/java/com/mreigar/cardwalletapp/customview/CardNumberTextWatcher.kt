package com.mreigar.cardwalletapp.customview

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.ImageView

class CardNumberTextWatcher(private val cardIcon: ImageView) : TextWatcher {

    companion object {
        const val CARD_NUMBER_MAX_LENGTH = 16
        private val nonDigits = Regex("[^\\d]")
    }

    private var current = ""

    override fun afterTextChanged(s: Editable) {
        if (s.toString() != current) {
            val userInput = s.toString().replace(nonDigits, "")

            if (userInput.length <= CARD_NUMBER_MAX_LENGTH) {
                current = userInput.chunked(4).joinToString(" ")
                s.filters = arrayOfNulls<InputFilter>(0)
            }

            s.replace(0, s.length, current, 0, current.length)

            cardIcon.setImageResource(CardUtils.getCardIcon(userInput))
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
}