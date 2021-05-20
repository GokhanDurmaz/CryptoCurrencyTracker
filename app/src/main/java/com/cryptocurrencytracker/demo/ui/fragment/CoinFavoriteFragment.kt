package com.cryptocurrencytracker.demo.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.cryptocurrencytracker.demo.R
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyData
import com.cryptocurrencytracker.demo.databinding.FragmentCoinFavoriteBinding
import com.cryptocurrencytracker.demo.helper.CryptoCurrencyDataHandler
import com.cryptocurrencytracker.demo.helper.observe
import com.cryptocurrencytracker.demo.ui.CryptoCurrencyTrackerVm
import com.cryptocurrencytracker.demo.ui.adapter.CryptoCurrencyTrackerAdapter
import com.cryptocurrencytracker.demo.ui.adapter.CryptoCurrencyTrackerDecorator
import com.cryptocurrencytracker.demo.ui.base.BaseFragment
import javax.inject.Inject

/**
 * Created by gokhan on 2/7/21.
 */

class CoinFavoriteFragment : BaseFragment<FragmentCoinFavoriteBinding>() {

    @Inject
    lateinit var cryptoCurrencyTrackerVm: CryptoCurrencyTrackerVm

    private lateinit var cryptoCurrencyTrackerAdapter: CryptoCurrencyTrackerAdapter

    override fun getFragmentViewBinding() = FragmentCoinFavoriteBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        fragmentBinding.favoriteCoins.apply {
            adapter = buildAdapter()
            addItemDecoration(
                CryptoCurrencyTrackerDecorator(
                    resources.getDimension(R.dimen.coin_margin).toInt()
                )
            )
        }
    }

    private fun buildAdapter(): CryptoCurrencyTrackerAdapter {
        cryptoCurrencyTrackerAdapter = CryptoCurrencyTrackerAdapter(
            cryptoCurrencyTrackerVm = cryptoCurrencyTrackerVm,
            coinSelected = {
                CryptoCurrencyDataHandler.onCoinDataChanged(it)
                findNavController().let { navController ->
                    navController.navigate(navController.graph.startDestination)
                    navController.navigate(R.id.action_mainFragment_to_coinDetail)
                }
            }
        )
        observe(cryptoCurrencyTrackerVm.getFavoriteCurrencies()) { list: List<CurrencyData>? ->
            if (list.isNullOrEmpty()) {
                Log.w("CoinFavoriteFragment", "Currency list is empty.")
                fragmentBinding.itemInfo.visibility = View.VISIBLE
            } else {
                cryptoCurrencyTrackerAdapter.setChanges(list.toMutableList())
                fragmentBinding.itemInfo.visibility = View.GONE
            }
        }
        return cryptoCurrencyTrackerAdapter
    }
}
