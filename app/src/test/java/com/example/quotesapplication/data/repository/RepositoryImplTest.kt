package com.example.quotesapplication.data.repository

import com.example.quotesapplication.data.api.NetworkDetails
import com.example.quotesapplication.data.model.QuotesItemModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class RepositoryImplTest{
    private lateinit var repositoryImpl: RepositoryImpl
    @Mock
    lateinit var networkDetails: NetworkDetails

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        repositoryImpl = RepositoryImpl(networkDetails)
    }

    @Test
    fun `getQuotes with data returned`() = runBlocking{
        whenever(networkDetails.getQuotes())
            .thenReturn(arrayListOf(QuotesItemModel(id = "111")))
        val result = repositoryImpl.getQuotes()
        assertEquals(arrayListOf(QuotesItemModel(id = "111")), result)
    }
}