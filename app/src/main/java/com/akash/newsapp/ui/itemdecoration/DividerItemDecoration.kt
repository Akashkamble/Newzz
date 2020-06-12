package com.akash.newsapp.ui.itemdecoration

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.akash.newsapp.utils.extensions.toPx

/**
 * Created by Akash on 2020-01-27
 */
class DividerItemDecoration(private val divider: Drawable) : ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val margin = child.left + params.leftMargin
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(margin, top, right - margin, bottom)
            divider.draw(c)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = DP_4.toPx().toInt()
        outRect.bottom = DP_4.toPx().toInt()
    }

    companion object {
        private const val DP_4 = 4.0f
    }
}
