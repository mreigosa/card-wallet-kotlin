package com.mreigar.localstorage.datasource

import com.mreigar.data.datasource.CardDatabaseDataSource
import com.mreigar.data.model.Card
import com.mreigar.localstorage.database.AppDatabase
import com.mreigar.localstorage.mapper.toDataEntity
import com.mreigar.localstorage.mapper.toDatabaseEntity
import org.koin.core.KoinComponent
import org.koin.core.inject

class CardDatabaseDataSourceImpl : CardDatabaseDataSource, KoinComponent {

    private val database: AppDatabase by inject()

    override fun getCards(): List<Card> {
        val cards = database.cardDao().getAllCards()
        return cards.map { it.toDataEntity() }
    }

    override fun saveCard(card: Card) {
        database.cardDao().insertCard(card.toDatabaseEntity())
    }


}