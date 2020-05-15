package com.mreigar.cardwalletapp.cardlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mreigar.cardwalletapp.mapper.toViewModel
import com.mreigar.cardwalletapp.model.CardViewModel
import com.mreigar.data.repository.CardRepository
import kotlinx.coroutines.launch

class CardListViewModel(private val repository: CardRepository) : ViewModel() {

    val wallet = MutableLiveData<List<CardViewModel>>()

    init {
        getWallet()
    }

    private fun getWallet() {
        viewModelScope.launch {
            val result = repository.getCards()
            wallet.postValue(result.map { it.toViewModel() })
        }
    }
}