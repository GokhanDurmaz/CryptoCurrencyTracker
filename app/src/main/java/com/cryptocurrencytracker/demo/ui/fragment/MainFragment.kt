package com.cryptocurrencytracker.demo.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cryptocurrencytracker.demo.R
import com.cryptocurrencytracker.demo.databinding.FragmentMainBinding
import com.cryptocurrencytracker.demo.helper.CoinsDataHandler
import com.cryptocurrencytracker.demo.helper.CryptoCurrencyDataHandler
import com.cryptocurrencytracker.demo.helper.observe
import com.cryptocurrencytracker.demo.helper.ui.CryptoCurrencyTrackerPaginationListener
import com.cryptocurrencytracker.demo.ui.CryptoCurrencyTrackerVm
import com.cryptocurrencytracker.demo.ui.adapter.CryptoCurrencyTrackerAdapter
import com.cryptocurrencytracker.demo.ui.adapter.CryptoCurrencyTrackerDecorator
import com.cryptocurrencytracker.demo.ui.base.BaseFragment
import com.cryptocurrencytracker.demo.ui.fragment.MainFragmentDirections.actionMainFragmentToCoinDetail
import javax.inject.Inject

/**
 * Created by gokhan on 2/6/21.
 */

class MainFragment : BaseFragment<FragmentMainBinding>() {

    @Inject
    lateinit var cryptoCurrencyTrackerVm: CryptoCurrencyTrackerVm

    private lateinit var cryptoCurrencyTrackerAdapter: CryptoCurrencyTrackerAdapter

    private var pageCount: Int = 0
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false

    override fun getFragmentViewBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
    }

    private fun setView() {
        setRecyclerView()
        setSearchView()
    }

    private fun setSearchView() {
        fragmentBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                fragmentBinding.search.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cryptoCurrencyTrackerAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun setRecyclerView() {
        val mLayoutManager = LinearLayoutManager(context)
        fragmentBinding.coins.apply {
            adapter = buildAdapter()
            addItemDecoration(
                CryptoCurrencyTrackerDecorator(
                    resources.getDimension(R.dimen.coin_margin).toInt()
                )
            )
            layoutManager = mLayoutManager
            addOnScrollListener(object :
                CryptoCurrencyTrackerPaginationListener(mLayoutManager) {
                override fun addMoreItem() {
                    isLoading = true
                    pageCount = pageCount.plus(1)

                    val searchQueryLength = fragmentBinding.search.query.length

                    val isPageCountEligible =
                        cryptoCurrencyTrackerVm.getCurrencyDataPageCount() >= pageCount

                    if (isPageCountEligible && searchQueryLength == 0)
                        refreshAdapter(pageCount)
                    else
                        isLastPage = true

                    isLoading = false
                }

                override fun isLoading(): Boolean = isLoading

                override fun isLastPage(): Boolean = isLastPage
            })
        }
    }

    private fun buildAdapter(): CryptoCurrencyTrackerAdapter {
        cryptoCurrencyTrackerAdapter = CryptoCurrencyTrackerAdapter(
            cryptoCurrencyTrackerVm = cryptoCurrencyTrackerVm,
            coinSelected = {
                CryptoCurrencyDataHandler.onCoinDataChanged(it)
                findNavController().navigate(actionMainFragmentToCoinDetail())
            }
        )
        refreshAdapter()
        return cryptoCurrencyTrackerAdapter
    }

    private fun refreshAdapter(page: Int = 1) =
        observe(cryptoCurrencyTrackerVm.getCurrenciesAsPageCount(page)) { currencyData ->
            if (currencyData.isNullOrEmpty()) {
                Log.w("MainFragment", "CurrencyData list is empty.")
            } else {
                cryptoCurrencyTrackerAdapter.setChanges(currencyData.toMutableList())
            }
        }

    override fun onPause() {
        fragmentBinding.search.clearFocus()
        super.onPause()
    }
}
