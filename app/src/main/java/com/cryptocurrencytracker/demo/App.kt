package com.cryptocurrencytracker.demo

import com.cryptocurrencytracker.demo.di.components.DaggerAppComponent
import dagger.android.DaggerApplication

/**
 * Created by gokhan on 2/6/21.
 */

class App : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent
        .factory()
        .create(this)
}
