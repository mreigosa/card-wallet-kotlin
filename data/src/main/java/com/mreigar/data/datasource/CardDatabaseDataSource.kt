package com.mreigar.data.datasource

import com.mreigar.data.model.Card

interface CardDatabaseDataSource {
    suspend fun getCards(): List<Card>
    suspend fun saveCard(card: Card)
}