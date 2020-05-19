package com.mreigar.cardwalletapp.camera

import com.mreigar.cardwalletapp.addcard.ScannedCardData

interface CardAnalyzerListener {
    fun onCardScanned(cardData: ScannedCardData)
    fun onError(e: Exception)
}