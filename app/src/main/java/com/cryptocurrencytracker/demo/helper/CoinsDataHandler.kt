package com.cryptocurrencytracker.demo.helper

import androidx.lifecycle.MutableLiveData
import com.cryptocurrencytracker.demo.data.db.currency.coin.CoinsDataItem
import com.cryptocurrencytracker.demo.data.db.currency.coin.detail.CoinsDetail

/**
 * Created by gokhan on 2/10/21.
 */

object CoinsDataHandler {

    val coin = MutableLiveData<CoinsDetail>()

    fun onCoinDataChanged(coinsDetail: CoinsDetail) {
        coin.postValue(coinsDetail)
    }
}
