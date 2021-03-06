package com.cryptocurrencytracker.demo.data.db.currency.coin.detail

import com.cryptocurrencytracker.demo.data.db.currency.coin.*

/**
 * Created by gokhan on 3/14/21.
 */

data class CoinsDetail(
    val additional_notices: List<Any>,
    val asset_platform_id: String,
    val block_time_in_minutes: Int,
    val categories: List<String>,
    val coingecko_rank: Int,
    val coingecko_score: Double,
    val community_data: CommunityData,
    val community_score: Double,
    val contract_address: String,
    val country_origin: String,
    val description: Description?,
    val developer_data: DeveloperData,
    val developer_score: Double,
    val genesis_date: Any,
    val hashing_algorithm: Any?,
    val id: String,
    val image: İmage,
    val last_updated: String,
    val links: Links,
    val liquidity_score: Double,
    val localization: Localization,
    val market_cap_rank: Int,
    val market_data: MarketData,
    val name: String,
    val platforms: Platforms,
    val public_interest_score: Double,
    val public_interest_stats: PublicİnterestStats,
    val public_notice: Any,
    val sentiment_votes_down_percentage: Double,
    val sentiment_votes_up_percentage: Double,
    val status_updates: List<Any>,
    val symbol: String,
    val tickers: List<Ticker>
)
