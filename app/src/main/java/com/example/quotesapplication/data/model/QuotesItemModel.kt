package com.example.quotesapplication.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QuotesItemModel(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("en")
    val en: String? = "",
    @SerializedName("id")
    val id: String
)