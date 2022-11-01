package com.example.quotesapplication.ui.quotes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapplication.data.model.QuotesItemModel
import com.example.quotesapplication.data.repository.Repository
import com.example.quotesapplication.data.repository.RepositoryImpl
import com.example.quotesapplication.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(val repository: Repository): ViewModel() {

    private var _quotes: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val quotes: StateFlow<UiState> get() = _quotes

    var currentQuote: QuotesItemModel? = null

    fun getQuotes(){
        viewModelScope.launch {
            try {
                val result = repository.getQuotes()
                if (result.isEmpty())
                    _quotes.value = UiState.Error("Empty Response")
                else
                    _quotes.value = UiState.Success(result)
            }catch (e: Exception){
                _quotes.value = UiState.Error("Error -> ${e.message}")
            }
        }
    }

}