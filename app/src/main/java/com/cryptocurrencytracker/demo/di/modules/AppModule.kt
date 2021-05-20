package com.cryptocurrencytracker.demo.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.cryptocurrencytracker.demo.App
import com.cryptocurrencytracker.demo.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by gokhan on 2/6/21.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(app: App): Application = app

    @Singleton
    @Provides
    fun providePreferences(app: App): SharedPreferences = app.getSharedPreferences(
        app.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )
}
