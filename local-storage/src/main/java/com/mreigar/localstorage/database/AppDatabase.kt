package com.mreigar.localstorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mreigar.localstorage.CardDatabaseEntity
import com.mreigar.localstorage.database.dao.CardDao

@Database(
    version = AppDatabaseHelper.DATABASE_VERSION,
    entities = [CardDatabaseEntity::class]
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun cardDao(): CardDao
}