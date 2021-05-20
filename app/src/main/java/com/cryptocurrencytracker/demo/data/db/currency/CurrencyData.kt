package com.cryptocurrencytracker.demo.data.db.currency

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by gokhan on 2/8/21.
 */
@Entity(tableName = "Currencies")
data class CurrencyData(
    @PrimaryKey(autoGenerate = true)
    val currencyId: Int = 0,
    val currencyUUID: String,
    val name: String,
    val lastUpdated: String,
    val smallImage: String,
    val largeImage: String,
    val symbol: String,
    val favorite: Boolean
)
