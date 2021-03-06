package com.mreigar.data

sealed class Result<out T>()

data class Success<out T>(val data: T) : Result<T>()
data class Error(val exception: Exception = Exception()) : Result<Nothing>()