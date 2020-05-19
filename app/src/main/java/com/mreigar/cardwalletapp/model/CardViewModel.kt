package com.mreigar.cardwalletapp.model

import java.io.Serializable

data class CardViewModel(
    val name: String,
    val number: String,
    val expiryDate: String,
    val cvv: String,
    val iconResId: Int
) : Serializable