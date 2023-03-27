package com.convector.david_000.convector_valute

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.convector.david_000.convector_valute.data.CurrencyItem
import com.convector.david_000.convector_valute.databinding.ItemCurrencyBinding

class CurrenciesAdapter(
    private val list: List<CurrencyItem>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CurrenciesAdapter.CurrencyHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrenciesAdapter.CurrencyHolder {
        val binding = ItemCurrencyBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyHolder(binding)
    }


    override fun onBindViewHolder(holder: CurrenciesAdapter.CurrencyHolder, position: Int) {
        holder.setItem(list[position])
    }

    inner class CurrencyHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: CurrencyItem) {
            with(binding) {
                root.setOnClickListener { onItemClick.invoke(adapterPosition) }
                binding.textCurrencyName.text = item.currencyName
                binding.textCurrencyAmount.text = item.currencyAmount
            }
        }
    }
}