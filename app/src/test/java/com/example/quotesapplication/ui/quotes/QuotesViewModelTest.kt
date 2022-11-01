package com.example.quotesapplication.ui.quotes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.quotesapplication.data.model.QuotesItemModel
import com.example.quotesapplication.data.repository.Repository
import com.example.quotesapplication.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.matchers.Not
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

class QuotesViewModelTest{

    val dispatcher = TestCoroutineDispatcher()
    @get:Rule
    var instantExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var quotesViewModel: QuotesViewModel

    @Mock
    lateinit var repository: Repository

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        quotesViewModel = QuotesViewModel(repository)
    }

    @Test
    fun `getQuotes with data`() = runBlocking{
        whenever(repository.getQuotes())
            .thenReturn(arrayListOf(QuotesItemModel(id = "123")))
        quotesViewModel.getQuotes()
        quotesViewModel.quotes.asLiveData().observeForever {
            assertEquals(UiState.Success(arrayListOf(QuotesItemModel(id = "123"))),it)
        }
    }

    @Test
    fun `getQuotes if no data returned`() = runBlocking{
        whenever(repository.getQuotes())
            .thenReturn(arrayListOf())
        quotesViewModel.getQuotes()
        quotesViewModel.quotes.asLiveData().observeForever {
            assertEquals(UiState.Error("Empty Response"),it)
        }
    }


}