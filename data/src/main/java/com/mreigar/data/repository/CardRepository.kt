package com.mreigar.data.repository

import com.mreigar.data.Result
import com.mreigar.data.model.Card

interface CardRepository {
    suspend fun addCard(card: Card) : Result<Boolean>
    suspend fun getCards(): List<Card>
}