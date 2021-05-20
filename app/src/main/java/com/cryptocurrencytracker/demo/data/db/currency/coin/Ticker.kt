package com.cryptocurrencytracker.demo.data.db.currency.coin

data class Ticker(
    val base: String,
    val bid_ask_spread_percentage: Double,
    val coin_id: String,
    val converted_last: ConvertedLast,
    val converted_volume: ConvertedVolume,
    val is_anomaly: Boolean,
    val is_stale: Boolean,
    val last: Double,
    val last_fetch_at: String,
    val last_traded_at: String,
    val market: Market,
    val target: String,
    val target_coin_id: String,
    val timestamp: String,
    val token_info_url: String,
    val trade_url: String,
    val trust_score: String,
    val volume: Double
)