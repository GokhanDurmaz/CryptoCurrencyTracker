package com.cryptocurrencytracker.demo.di.modules

import androidx.room.Room
import com.cryptocurrencytracker.demo.App
import com.cryptocurrencytracker.demo.data.db.CurrencyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by gokhan on 2/8/21.
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCurrencyDatabase(app: App) = Room
        .databaseBuilder(app, CurrencyDatabase::class.java, "Currency.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideCurrencyDao(currencyDatabase: CurrencyDatabase) = currencyDatabase.getCurrencyDao()
}