package com.cryptocurrencytracker.demo.di.modules

import com.cryptocurrencytracker.demo.di.InjectionViewModelProvider
import com.cryptocurrencytracker.demo.helper.CoinsDataHandler
import com.cryptocurrencytracker.demo.ui.CryptoCurrencyTrackerVm
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import javax.inject.Singleton

/**
 * Created by gokhan on 2/6/21.
 */
@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideCryptoCurrencyTrackerVm(
        fragment: DaggerFragment,
        viewModelProvider: InjectionViewModelProvider<CryptoCurrencyTrackerVm>
    ) = viewModelProvider.get(fragment, CryptoCurrencyTrackerVm::class)
}