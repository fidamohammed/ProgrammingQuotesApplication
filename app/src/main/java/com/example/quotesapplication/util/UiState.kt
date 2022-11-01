package com.example.quotesapplication.util

sealed class UiState{

    object Loading: UiState()
    data class Success<T>(val quotesResponse: T): UiState()
    data class Error(val error: String?): UiState()

}