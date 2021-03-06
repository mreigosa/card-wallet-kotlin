package com.mreigar.localstorage.injection

import com.mreigar.data.datasource.CardDatabaseDataSource
import com.mreigar.localstorage.database.AppDatabaseHelper
import com.mreigar.localstorage.datasource.CardDatabaseDataSourceImpl
import org.koin.dsl.module

object LocalStorageModules {

    val databaseModule = module {
        single { AppDatabaseHelper.getDatabase(get()) }

        factory<CardDatabaseDataSource> { CardDatabaseDataSourceImpl() }
    }
}