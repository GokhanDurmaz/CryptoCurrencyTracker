package com.cryptocurrencytracker.demo.data.db.currency.coin

import com.google.gson.annotations.SerializedName

data class Platforms(
    val ethereum: String,
    @SerializedName("huobi-token") val huobiToken: String
)