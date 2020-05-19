package com.mreigar.cardwalletapp.addcard

import java.io.Serializable

class ScannedCardData(
    val name: String,
    val number: String,
    val expiryDate: String
) : Serializable