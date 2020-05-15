package com.mreigar.cardwalletapp

import android.app.Application
import com.mreigar.cardwalletapp.injection.AppModules
import com.mreigar.data.injection.DataModules
import com.mreigar.localstorage.injection.LocalStorageModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CardWalletApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoinModules()
    }

    private fun initKoinModules() {
        startKoin {
            androidContext(this@CardWalletApp)
            modules(
                LocalStorageModules.databaseModule,
                DataModules.repositoryModule,
                AppModules.presentationModules
            )
        }
    }
}