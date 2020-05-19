package com.mreigar.cardwalletapp.customview

import com.mreigar.cardwalletapp.R

object CardUtils {

    enum class CardType { VISA, MASTERCARD, AMEX, DISCOVER, MAESTRO }

    val visa = Regex("^4[0-9]{12}(?:[0-9]{3})?$")
    val mastercard = Regex("^5[1-5][0-9]{14}$")
    val amx = Regex("^3[47][0-9]{5,}\$") //Regex("^3[47][0-9]{13}$")
    val discover = Regex("^6(?:011|5[0-9]{2})[0-9]{3,}$")
    val maestro = Regex("^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}\$")

    fun getCardIcon(cardNumber: String): Int {
        val number = cardNumber.replace(" ", "")

        return when {
            amx.matches(number) -> R.drawable.ic_amex
            visa.matches(number) -> R.drawable.ic_visa
            maestro.matches(number) -> R.drawable.ic_maestro
            discover.matches(number) -> R.drawable.ic_discover
            mastercard.matches(number) -> R.drawable.ic_mastercard
            else -> R.drawable.ic_card_generic
        }
    }

    fun isValidCard(cardNumber: String): Boolean {
        val number = cardNumber.replace(" ", "")

        return amx.matches(number) || visa.matches(number) || maestro.matches(number) || discover.matches(number) || mastercard.matches(number)
    }

    fun getCardType(cardNumber: String): CardType? {
        val number = cardNumber.replace(" ", "")

        return when {
            amx.matches(number) -> CardType.AMEX
            visa.matches(number) -> CardType.VISA
            maestro.matches(number) -> CardType.MAESTRO
            discover.matches(number) -> CardType.DISCOVER
            mastercard.matches(number) -> CardType.MASTERCARD
            else -> null
        }
    }
}