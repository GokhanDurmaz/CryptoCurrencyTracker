/**
 * Copyright by gokhan on 2/6/21.
 */

package com.cryptocurrencytracker.demo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptocurrencytracker.demo.App
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyData
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyExecutor
import com.cryptocurrencytracker.demo.data.db.currency.coin.detail.CoinsDetail
import com.cryptocurrencytracker.demo.data.network.CryptoCurrencyTrackerService
import com.cryptocurrencytracker.demo.helper.CoinsDataHandler
import com.cryptocurrencytracker.demo.helper.util.isConnectionEligible
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CryptoCurrencyTrackerVm @Inject constructor(
    private val cryptoCurrencyTrackerService: CryptoCurrencyTrackerService,
    private val currencyExecutor: CurrencyExecutor,
    private val app: App
) : ViewModel() {

    init {
        viewModelScope.launch {
            if (currencyExecutor.currencyExist()) {
                Log.i(TAG, "There is the currency data.")
                return@launch
            }
            if (app.isConnectionEligible().not()) {
                Log.i(TAG, "There is already currency data")
                return@launch
            }
            cryptoCurrencyTrackerService.getCoinList().apply {
                if (isSuccessful) {
                    body()?.forEach { coinsDataItem ->
                        currencyExecutor.insert(
                            CurrencyData(
                                currencyUUID = coinsDataItem.id,
                                name = coinsDataItem.name,
                                lastUpdated = coinsDataItem.last_updated,
                                smallImage = coinsDataItem.image.small,
                                largeImage = coinsDataItem.image.large,
                                symbol = coinsDataItem.symbol,
                                favorite = false
                            )
                        )
                    }
                }
            }
        }
    }

    fun getCurrenciesAsPageCount(page: Int): LiveData<List<CurrencyData>> =
        currencyExecutor.getCurrenciesAsPageCount(page)

    fun getCurrenciesAsNameOrSymbol(constraint: String = ""): MutableList<CurrencyData> =
        currencyExecutor.getCurrenciesAsNameOrSymbol(constraint)

    fun getFavoriteCurrencies(): LiveData<List<CurrencyData>> =
        currencyExecutor.getFavoriteCurrencies()

    fun getCurrencyDataPageCount(): Int = currencyExecutor.getCurrencyDataCount() / MAX_ITEM_COUNT

    fun getCoinsById(coinId: String) = try {
        cryptoCurrencyTrackerService.getCoinDetail(coinId).apply {
            this.enqueue(object : Callback<CoinsDetail> {
                override fun onResponse(call: Call<CoinsDetail>, response: Response<CoinsDetail>) {
                    response.apply {
                        if (isSuccessful) {
                            body()?.also {
                                CoinsDataHandler.onCoinDataChanged(it)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<CoinsDetail>, t: Throwable) {
                    Log.e(TAG, "${t.message}")
                }
            })
        }
    } catch (e: Exception) {
        Log.e(TAG, "${e.message}")
    }

    fun updateCurrency(currencyData: CurrencyData) = currencyExecutor.update(currencyData)

    companion object {
        private val TAG = CryptoCurrencyTrackerVm::class.java.name
        private const val MAX_ITEM_COUNT = 10
    }
}
