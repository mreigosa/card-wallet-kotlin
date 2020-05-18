package com.mreigar.localstorage.mapper

import com.mreigar.data.model.Card
import com.mreigar.localstorage.CardDatabaseEntity

fun CardDatabaseEntity.toDataEntity() = Card(name = name, number = number, expiryDate = expiryDate, cvv = cvv)

fun Card.toDatabaseEntity() = CardDatabaseEntity(name = name, number = number, expiryDate = expiryDate, cvv = cvv)