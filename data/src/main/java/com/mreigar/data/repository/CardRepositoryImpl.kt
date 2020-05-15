package com.mreigar.data.repository

import com.mreigar.data.datasource.CardDatabaseDataSource
import com.mreigar.data.model.Card

class CardRepositoryImpl(
    private val databaseDataSource: CardDatabaseDataSource
) : CardRepository {

    override fun addCard(card: Card) {
        databaseDataSource.saveCard(card)
    }

    override fun getCards(): List<Card> {
        return listOf(
            Card("MARTIN", "1111222233334444", "01/01"),
            Card("MARTIN", "5555666677778888", "02/02"),
            Card("MARTIN", "0101020203030404", "03/03")
        )
    }


}