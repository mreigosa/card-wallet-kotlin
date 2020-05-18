package com.mreigar.cardwalletapp.cardlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mreigar.cardwalletapp.mapper.toViewModel
import com.mreigar.cardwalletapp.model.CardViewModel
import com.mreigar.data.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardListViewModel(private val repository: CardRepository) : ViewModel() {

    private val viewState = CardListViewState()
    val state: MutableLiveData<CardListViewState> = MutableLiveData(viewState)

    init {
        getWallet()
    }

    private fun getWallet() {
        state.postValue(viewState.copy(isLoading = true))
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                repository.getCards()
            }
            state.postValue(CardListViewState(false, result.map { it.toViewModel() }))
        }
    }
}

data class CardListViewState(
    val isLoading: Boolean = false,
    val cards: List<CardViewModel> = emptyList()
)