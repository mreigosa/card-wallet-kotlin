package com.mreigar.data.datasource

import com.mreigar.data.model.Card

interface CardDatabaseDataSource {
    fun getCards(): List<Card>
    fun saveCard(card: Card)
}