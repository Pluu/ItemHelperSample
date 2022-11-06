package com.pluu.sample.itemhelper

import android.graphics.Color
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SampleAdapter(
    private val onItemLongClick: (holder: RecyclerView.ViewHolder) -> Unit
) : RecyclerView.Adapter<SampleViewHolder>() {

    private val MOVEABLE_LIMIT_INDEX = 10

    private val list = (0..25).map {
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

    fun canDropOver(
        recyclerView: RecyclerView,
        current: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Log.d(
            "TAG",
            "canDropOver current=${current.bindingAdapterPosition} target=${target.bindingAdapterPosition}"
        )
        if (target.bindingAdapterPosition == 0) {
            return false
        } else if (target.bindingAdapterPosition < MOVEABLE_LIMIT_INDEX && current.bindingAdapterPosition >= MOVEABLE_LIMIT_INDEX) {
            return false
        }
        return true
    }

    fun onMove(from: Int, to: Int) {
        Log.d("TAG", "onMove from=${from}, to=${to}")
        list.add(to, list.removeAt(from))
        notifyItemMoved(from, to)
    }
}
