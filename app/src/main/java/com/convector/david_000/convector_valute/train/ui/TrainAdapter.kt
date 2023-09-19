package com.convector.david_000.convector_valute.train.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.convector.david_000.convector_valute.data.TrainItem
import com.convector.david_000.convector_valute.databinding.ItemTrainBinding

class TrainAdapter(
    private val list: List<TrainItem>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<TrainAdapter.TrainHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainHolder {
        val binding = ItemTrainBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainHolder(binding)
    }


    override fun onBindViewHolder(holder: TrainHolder, position: Int) {
        holder.setItem(list[position])
    }

    inner class TrainHolder(private val binding: ItemTrainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: TrainItem) {
            with(binding) {
                root.setOnClickListener { onItemClick.invoke(adapterPosition) }
                binding.cityStartText.text = item.stationNameFrom
                binding.cityEndText.text = item.stationTo
                binding.timeStartText.text = item.fromTime
                binding.timeEndText.text = item.toTime
                binding.timeSpendText.text = item.timeInWay
            }
        }
    }
}