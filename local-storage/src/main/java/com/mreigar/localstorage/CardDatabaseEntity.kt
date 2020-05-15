package com.mreigar.localstorage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardDatabaseEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "expiry_date") val expiryDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var cardId: Int = 0
}