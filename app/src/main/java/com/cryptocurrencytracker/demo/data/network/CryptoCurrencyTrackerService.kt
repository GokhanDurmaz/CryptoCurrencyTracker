package com.cryptocurrencytracker.demo.data.network

import com.cryptocurrencytracker.demo.data.db.currency.coin.CoinsData
import com.cryptocurrencytracker.demo.data.db.currency.coin.detail.CoinsDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by gokhan on 2/7/21.
 */

interface CryptoCurrencyTrackerService {

    @GET("api/v3/coins")
    suspend fun getCoinList(): Response<CoinsData>

    @GET("api/v3/coins/{id}")
    fun getCoinDetail(@Path("id") coinId: String): Call<CoinsDetail>
}
