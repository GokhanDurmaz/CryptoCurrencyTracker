/**
 * Created by gokhan on 2/7/21.
 */

package com.cryptocurrencytracker.demo.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.cryptocurrencytracker.demo.R
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyData
import com.cryptocurrencytracker.demo.data.db.currency.coin.detail.CoinsDetail
import com.cryptocurrencytracker.demo.databinding.FragmentCoinDetailBinding
import com.cryptocurrencytracker.demo.helper.CoinsDataHandler
import com.cryptocurrencytracker.demo.helper.CryptoCurrencyDataHandler
import com.cryptocurrencytracker.demo.helper.ENCODING
import com.cryptocurrencytracker.demo.helper.MIME_TYPE
import com.cryptocurrencytracker.demo.helper.ui.loadImage
import com.cryptocurrencytracker.demo.ui.CryptoCurrencyTrackerVm
import com.cryptocurrencytracker.demo.ui.base.BaseFragment
import javax.inject.Inject

class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>() {

    @Inject
    lateinit var cryptoCurrencyTrackerVm: CryptoCurrencyTrackerVm

    override fun getFragmentViewBinding() = FragmentCoinDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun setView() {
        CryptoCurrencyDataHandler.coinsList.observe(
            viewLifecycleOwner,
            Observer(::currencyDataHandler)
        )
        CoinsDataHandler.coin.observe(
            viewLifecycleOwner,
            Observer(::coinDataHandler)
        )
    }

    private fun currencyDataHandler(currencyData: CurrencyData) {
        fragmentBinding.largeIcon.loadImage(currencyData.largeImage)
        context?.let { safeContext ->
            fragmentBinding.favorite.background = if (currencyData.favorite)
                ContextCompat.getDrawable(safeContext, R.drawable.custom_favorite_button)
            else ContextCompat.getDrawable(safeContext, R.drawable.custom_not_favorite_button)
        }

        fragmentBinding.favorite.setOnClickListener {
            val updatedCurrencyData = CurrencyData(
                currencyId = currencyData.currencyId,
                currencyUUID = currencyData.currencyUUID,
                name = currencyData.name,
                lastUpdated = currencyData.lastUpdated,
                smallImage = currencyData.smallImage,
                largeImage = currencyData.largeImage,
                symbol = currencyData.symbol,
                favorite = currencyData.favorite.not()
            )
            cryptoCurrencyTrackerVm.updateCurrency(updatedCurrencyData)
            CryptoCurrencyDataHandler.onCoinDataChanged(updatedCurrencyData)
        }
        cryptoCurrencyTrackerVm.getCoinsById(currencyData.currencyUUID)
    }

    private fun coinDataHandler(coinsDetail: CoinsDetail) {
        fragmentBinding.algorithm.text = StringBuilder(
            "Hashing Algorithm: ${
                coinsDetail.hashing_algorithm ?: resources.getString(R.string.unknown)
            }"
        )
        fragmentBinding.description.loadData(
            (coinsDetail.description?.tr ?: resources.getString(R.string.not_found)),
            MIME_TYPE,
            ENCODING
        )
        fragmentBinding.coinName.text = coinsDetail.name
        fragmentBinding.coinId.text = coinsDetail.id
        coinsDetail.market_data.current_price?.also { currentPrice ->
            fragmentBinding.currentPrice.text = StringBuilder(
                "${currentPrice.`try`}"
            )

            fragmentBinding.currentPrice.apply {
                if (currentPrice.`try` > 0)
                    setTextColor(ContextCompat.getColor(context, R.color.colorLightGreen))
                else
                    setTextColor(ContextCompat.getColor(context, R.color.colorLightRed))

                text = StringBuilder("${currentPrice.`try`}")
            }
        }

        fragmentBinding.priceChangeIn24h.apply {
            if (coinsDetail.market_data.price_change_24h_in_currency.`try` > 0)
                setTextColor(ContextCompat.getColor(context, R.color.colorLightGreen))
            else
                setTextColor(ContextCompat.getColor(context, R.color.colorLightRed))
            text = StringBuilder("${coinsDetail.market_data.price_change_24h_in_currency.`try`}")
        }
    }
}
