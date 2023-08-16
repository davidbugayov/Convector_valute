package com.convector.david_000.convector_valute.autofill.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.databinding.ItemSuggestedBinding

class SuggestedAdapter(
    private val list: List<StationItem>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<SuggestedAdapter.SuggestedHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuggestedHolder {
        val binding = ItemSuggestedBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestedHolder(binding)
    }


    override fun onBindViewHolder(holder: SuggestedHolder, position: Int) {
        holder.setItem(list[position])
    }

    inner class SuggestedHolder(private val binding: ItemSuggestedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: StationItem) {
            with(binding) {
                root.setOnClickListener { onItemClick.invoke(adapterPosition) }
                binding.nameText.text = item.name
                binding.codeText.text = item.code.toString()
            }
        }
    }
}