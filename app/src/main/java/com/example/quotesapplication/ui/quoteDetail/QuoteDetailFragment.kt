package com.example.quotesapplication.ui.quoteDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.quotesapplication.R
import com.example.quotesapplication.data.model.QuotesItemModel
import com.example.quotesapplication.databinding.FragmentQuoteDetailBinding
import com.example.quotesapplication.ui.quotes.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.Typography.quote

@AndroidEntryPoint
class QuoteDetailFragment : Fragment() {
    private lateinit var binding: FragmentQuoteDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuoteDetailBinding.inflate(inflater)
        val quotesViewModel: QuotesViewModel by activityViewModels()
        val quote = quotesViewModel.currentQuote
        binding.apply {
            tvDetailQuote.text = quote?.en
            tvDetailAuthor.text = quote?.author
        }
        return binding.root
    }


}