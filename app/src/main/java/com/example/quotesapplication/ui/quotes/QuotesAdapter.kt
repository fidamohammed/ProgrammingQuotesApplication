package com.example.quotesapplication.ui.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapplication.R
import com.example.quotesapplication.data.model.QuotesItemModel
import com.example.quotesapplication.databinding.QuoteItemBinding

class QuotesAdapter(val quotesList: ArrayList<QuotesItemModel>,
                    val clickHandler: ((quoteItem: QuotesItemModel) -> Unit))
    :RecyclerView.Adapter<QuotesAdapter.ItemViewHolder>() {
     class ItemViewHolder(quoteItem: View): RecyclerView.ViewHolder(quoteItem) {
        val binding = QuoteItemBinding.bind(quoteItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentQuote = quotesList[position]
        holder.binding.tvQuote.text = currentQuote.en
        holder.binding.root.setOnClickListener{
            clickHandler(currentQuote)
        }
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }
}