package com.mreigar.cardwalletapp.addcard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mreigar.data.Result
import com.mreigar.data.model.Card
import com.mreigar.data.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCardViewModel(private val repository: CardRepository) : ViewModel() {

    private val viewState = AddCardViewState()

    val state: MutableLiveData<AddCardViewState> = MutableLiveData(viewState)

    fun addCardToWallet(holderName: String, cardNumber: String, expiryDate: String, cvv: String) {
        if (holderName.isEmpty() || cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
            state.postValue(viewState.copy(emptyFieldsError = true))
        } else {
            state.postValue(viewState.copy(isLoading = true))
            viewModelScope.launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    repository.addCard(card = Card(holderName, cardNumber, expiryDate, cvv))
                }
                state.postValue(AddCardViewState(addCardResult = result))
            }
        }
    }
}

data class AddCardViewState(
    val isLoading: Boolean = false,
    val emptyFieldsError: Boolean = false,
    val addCardResult: Result<Boolean>? = null
)