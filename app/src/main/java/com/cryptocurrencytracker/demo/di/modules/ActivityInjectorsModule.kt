package com.cryptocurrencytracker.demo.di.modules

import com.cryptocurrencytracker.demo.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by gokhan on 2/7/21.
 */
@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity
}
