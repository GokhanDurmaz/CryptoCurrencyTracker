package com.cryptocurrencytracker.demo.helper

import androidx.lifecycle.MutableLiveData
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyData

/**
 * Created by gokhan on 2/8/21.
 */

object CryptoCurrencyDataHandler {

    val coinsList = MutableLiveData<CurrencyData>()

    fun onCoinDataChanged(currencyData: CurrencyData) {
        coinsList.postValue(currencyData)
    }
}
