package com.mreigar.localstorage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mreigar.localstorage.CardDatabaseEntity

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(card: CardDatabaseEntity)

    @Query("SELECT * FROM CardDatabaseEntity")
    fun getAllCards(): List<CardDatabaseEntity>
}