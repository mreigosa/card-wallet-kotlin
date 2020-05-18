package com.mreigar.cardwalletapp.mapper

import com.mreigar.cardwalletapp.customview.CardUtils
import com.mreigar.cardwalletapp.model.CardViewModel
import com.mreigar.data.model.Card

fun CardViewModel.toDataEntity() = Card(name = name, number = number, expiryDate = expiryDate, cvv = cvv)

fun Card.toViewModel() = CardViewModel(name = name, number = number, expiryDate = expiryDate, iconResId = CardUtils.getCardIcon(number), cvv = cvv)