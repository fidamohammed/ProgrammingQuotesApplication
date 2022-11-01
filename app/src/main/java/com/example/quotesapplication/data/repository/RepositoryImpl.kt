package com.example.quotesapplication.data.repository

import com.example.quotesapplication.data.api.NetworkDetails
import com.example.quotesapplication.data.model.QuotesItemModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val networkDetails: NetworkDetails): Repository {
    override suspend fun getQuotes(): ArrayList<QuotesItemModel> =
        networkDetails.getQuotes()

}