package com.example.quotesapplication.ui.quotes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapplication.R
import com.example.quotesapplication.data.model.QuotesItemModel
import com.example.quotesapplication.databinding.FragmentQuotesBinding
import com.example.quotesapplication.util.UiState
import com.example.quotesapplication.util.checkForInternet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesFragment : Fragment() {

    private lateinit var binding: FragmentQuotesBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQuotesBinding.inflate(inflater)
        recyclerView = binding.rvAllQuotes
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        val quotesViewModel: QuotesViewModel by activityViewModels()

        if(checkForInternet(requireContext())){
            quotesViewModel.getQuotes()
            quotesViewModel.quotes.asLiveData().observe(viewLifecycleOwner){ state ->
                when(state){
                    is UiState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is UiState.Error ->{
                        binding.progressBar.isVisible = false
                        Toast.makeText(context,"${state.error} ",Toast.LENGTH_SHORT).show()
                    }
                    is UiState.Success<*> -> {
                        binding.progressBar.isVisible = false
                        recyclerView.adapter = QuotesAdapter(state.quotesResponse as ArrayList<QuotesItemModel> ){
                            quotesViewModel.currentQuote = it
                            findNavController().navigate(R.id.action_quotesFragment_to_quoteDetailFragment)
                        }
                    }
                }

            }
        }
        else{
            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

}