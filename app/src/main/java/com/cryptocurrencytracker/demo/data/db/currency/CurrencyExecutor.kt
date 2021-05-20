package com.cryptocurrencytracker.demo.data.db.currency

import dagger.Reusable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by gokhan on 2/8/21.
 */
@Reusable
class CurrencyExecutor @Inject constructor(
    private val currencyDao: CurrencyDao
) {

    fun currencyExist() = runBlocking {
        withContext(Dispatchers.IO) {
            currencyDao.currencyExist()
        }
    }

    fun getCurrenciesAsPageCount(page: Int) = runBlocking {
        withContext(Dispatchers.IO) {
            currencyDao.getCurrenciesAsPageCount(page)
        }
    }

    fun getCurrenciesAsNameOrSymbol(constraint: String) = runBlocking {
        withContext(Dispatchers.IO) {
            currencyDao.getCurrenciesAsNameOrSymbol(constraint)
        }
    }

    fun getFavoriteCurrencies() = runBlocking {
        withContext(Dispatchers.IO) {
            currencyDao.getFavoriteCurrencies()
        }
    }

    fun getCurrencyDataCount() = runBlocking {
        withContext(Dispatchers.IO) {
            currencyDao.getCurrencyDataCount()
        }
    }

    fun insert(currencyData: CurrencyData) = runBlocking {
        withContext(Dispatchers.IO) {
            currencyDao.insert(currencyData)
        }
    }

    fun update(currencyData: CurrencyData) = runBlocking {
        withContext(Dispatchers.IO) {
            currencyDao.update(currencyData)
        }
    }

    fun delete(currencyData: CurrencyData) = runBlocking {
        withContext(Dispatchers.IO) {
            currencyDao.delete(currencyData)
        }
    }
}
