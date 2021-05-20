package com.cryptocurrencytracker.demo.di.components

import com.cryptocurrencytracker.demo.App
import com.cryptocurrencytracker.demo.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by gokhan on 2/6/21.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityInjectorsModule::class,
        DatabaseModule::class,
        FragmentInjectorsModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: App): AppComponent
    }
}
