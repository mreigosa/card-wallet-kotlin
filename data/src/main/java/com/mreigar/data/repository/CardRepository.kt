package com.mreigar.data.repository

import com.mreigar.data.model.Card

interface CardRepository {
    fun addCard(card: Card)
    fun getCards(): List<Card>
}