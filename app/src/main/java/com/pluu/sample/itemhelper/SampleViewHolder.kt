package com.pluu.sample.itemhelper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pluu.sample.itemhelper.databinding.ItemSampleBinding

class SampleViewHolder(
    private val binding: ItemSampleBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Item) {
        binding.cardView.setCardBackgroundColor(item.color)
        binding.positionText.text = "Position #${item.position}"
    }

    companion object {
        fun newInstance(parent: ViewGroup): SampleViewHolder {
            return SampleViewHolder(
                ItemSampleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}