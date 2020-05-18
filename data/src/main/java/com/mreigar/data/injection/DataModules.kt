package com.mreigar.data.injection

import com.mreigar.data.repository.CardRepository
import com.mreigar.data.repository.CardRepositoryImpl
import org.koin.dsl.module

object DataModules {

    val repositoryModule = module {
        factory<CardRepository> { CardRepositoryImpl(get()) }
    }
}