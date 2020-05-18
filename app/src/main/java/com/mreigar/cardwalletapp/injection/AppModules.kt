package com.mreigar.cardwalletapp.injection

import com.mreigar.cardwalletapp.addcard.AddCardViewModel
import com.mreigar.cardwalletapp.cardlist.CardListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {

    val presentationModules = module {
        viewModel { CardListViewModel(get()) }
        viewModel { AddCardViewModel(get()) }
    }
}