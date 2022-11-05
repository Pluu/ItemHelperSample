package com.pluu.sample.itemhelper

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SampleAdapter(
    private val onItemLongClick: (holder: RecyclerView.ViewHolder) -> Unit
) : RecyclerView.Adapter<SampleViewHolder>() {
    private val list = (0..40).map {
        Item(
            position = it,
            color = Color.rgb(
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            )
        )
    }.toMutableList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder.newInstance(parent).apply {
            itemView.setOnLongClickListener {
                onItemLongClick(this)
                true
            }
        }

    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long {
        return list[position].hashCode().toLong()
    }

    fun onMove(from: Int, to: Int) {
        list.add(to, list.removeAt(from))
        notifyItemMoved(from, to)
    }
}
