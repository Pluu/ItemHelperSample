package com.pluu.sample.itemhelper

import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pluu.sample.itemhelper.utils.dp2px


class SampleItemDecoration : RecyclerView.ItemDecoration() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 4f
        pathEffect = DashPathEffect(floatArrayOf(80f, 30f), 0f)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount: Int = parent.childCount
        for (i in 1 until childCount) {
            val child = parent.getChildAt(i)
            val params = parent.getChildAdapterPosition(child)
            if (params == 10) {
                c.save()
                val startX = child.left
                val stopX = child.right
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.top - params.topMargin - child.context.dp2px(20f)
                c.drawLine(startX.toFloat(), top.toFloat(), stopX.toFloat(), top.toFloat(), paint)
                c.restore()
                return
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position == 10) {
            outRect.top = view.context.dp2px(40f)
        }
    }
}