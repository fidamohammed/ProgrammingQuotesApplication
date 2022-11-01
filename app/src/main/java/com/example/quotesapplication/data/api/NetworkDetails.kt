package com.example.quotesapplication.data.api

import com.example.quotesapplication.data.model.QuotesItemModel
import retrofit2.http.GET

interface NetworkDetails {

    @GET("quotes")
    suspend fun getQuotes(): ArrayList<QuotesItemModel>

}