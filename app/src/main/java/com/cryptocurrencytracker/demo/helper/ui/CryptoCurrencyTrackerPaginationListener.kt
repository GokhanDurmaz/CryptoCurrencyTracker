package com.cryptocurrencytracker.demo.helper.ui

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by gokhan on 2/12/21.
 */

abstract class CryptoCurrencyTrackerPaginationListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (isLoading().not() && isLastPage().not()) {
            if ((firstVisibleItemPosition + visibleItemCount) >= totalItemCount) {
                addMoreItem()
                Log.d(TAG, "New items are added.")
            }
        }
    }

    abstract fun addMoreItem()

    abstract fun isLoading(): Boolean

    abstract fun isLastPage(): Boolean

    companion object {
        private val TAG = CryptoCurrencyTrackerPaginationListener::class.java.name
    }
}
