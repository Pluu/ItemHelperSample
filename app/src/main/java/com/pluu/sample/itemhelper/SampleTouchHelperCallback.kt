package com.pluu.sample.itemhelper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SampleTouchHelperCallback : ItemTouchHelper.Callback() {

    private lateinit var adapter: SampleAdapter

    fun setAdapter(adapter: SampleAdapter) {
        this.adapter = adapter
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun getAnimationDuration(
        recyclerView: RecyclerView,
        animationType: Int,
        animateDx: Float,
        animateDy: Float
    ): Long {
        return if (animateDy == 0f) {
            // 이동거리가 없다면, 0ms 로 처리
            0L
        } else {
            super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy)
        }
    }

    override fun canDropOver(
        recyclerView: RecyclerView,
        current: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return adapter.canDropOver(recyclerView, current, target)
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            0
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onMove(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition)
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        val view = viewHolder?.itemView ?: return
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG -> {
                with(viewHolder.itemView) {
                    alpha = 0.85f
                    scaleX = 1.04f
                    scaleY = 1.04f
                }
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        with(viewHolder.itemView) {
            alpha = 1f
            scaleX = 1f
            scaleY = 1f
        }
    }
}