package com.cryptocurrencytracker.demo.data.db.currency

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

/**
 * Created by gokhan on 2/8/21.
 */
@Dao
interface CurrencyDao {

    @Query("SELECT CAST(CASE WHEN(SELECT COUNT(*) FROM Currencies) > 0 THEN 1 ELSE 0 END AS BIT)")
    fun currencyExist(): Boolean

    @Query("SELECT * FROM Currencies ORDER BY name ASC LIMIT 10 * :page")
    fun getCurrenciesAsPageCount(page: Int): LiveData<List<CurrencyData>>

    @Query("SELECT * FROM Currencies WHERE LOWER(name) LIKE :constraint OR LOWER(symbol) LIKE :constraint")
    fun getCurrenciesAsNameOrSymbol(constraint: String): MutableList<CurrencyData>

    @Query("SELECT * FROM Currencies WHERE favorite = :state ORDER BY name ASC")
    fun getFavoriteCurrencies(state: Boolean = true): LiveData<List<CurrencyData>>

    @Query("SELECT COUNT(*) FROM Currencies")
    fun getCurrencyDataCount(): Int

    @Insert
    fun insert(currencyData: CurrencyData)

    @Update
    fun update(currencyData: CurrencyData)

    @Delete
    fun delete(currencyData: CurrencyData)
}
