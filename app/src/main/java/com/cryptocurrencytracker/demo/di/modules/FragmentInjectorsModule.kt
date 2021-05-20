package com.cryptocurrencytracker.demo.di.modules

import com.cryptocurrencytracker.demo.ui.fragment.CoinDetailFragment
import com.cryptocurrencytracker.demo.ui.fragment.CoinFavoriteFragment
import com.cryptocurrencytracker.demo.ui.fragment.MainFragment
import com.cryptocurrencytracker.demo.ui.fragment.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by gokhan on 2/7/21.
 */
@Module
abstract class FragmentInjectorsModule {

    @ContributesAndroidInjector
    abstract fun mainFragmentInjectors(): MainFragment

    @ContributesAndroidInjector
    abstract fun coinDetailFragmentInjectors(): CoinDetailFragment

    @ContributesAndroidInjector
    abstract fun coinFavoriteFragmentInjectors(): CoinFavoriteFragment

    @ContributesAndroidInjector
    abstract fun settingsFragmentInjectors(): SettingsFragment
}
