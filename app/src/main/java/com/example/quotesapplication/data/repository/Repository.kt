package com.example.quotesapplication.data.repository

import com.example.quotesapplication.data.model.QuotesItemModel

interface Repository {
    suspend fun getQuotes(): ArrayList<QuotesItemModel>
}