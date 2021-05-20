package com.cryptocurrencytracker.demo.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by gokhan on 2/9/21.
 */

class CryptoCurrencyTrackerDecorator(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val itemCount = parent.adapter?.itemCount ?: 0
            val itemPosition = parent.getChildAdapterPosition(view)

            left = space / 5
            top = if (itemPosition == 0) space / 5 else space / 10
            right = space / 5
            bottom = if (itemPosition == itemCount - 1) space / 5 else space / 10
        }
    }
}
