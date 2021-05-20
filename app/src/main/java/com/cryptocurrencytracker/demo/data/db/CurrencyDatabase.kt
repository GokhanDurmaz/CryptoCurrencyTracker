package com.cryptocurrencytracker.demo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyDao
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyData

/**
 * Created by gokhan on 2/7/21.
 */
@Database(entities = [CurrencyData::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase: RoomDatabase() {

    abstract fun getCurrencyDao(): CurrencyDao
}
