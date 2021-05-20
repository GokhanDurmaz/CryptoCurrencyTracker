package com.cryptocurrencytracker.demo.di.modules

import com.cryptocurrencytracker.demo.data.network.CryptoCurrencyTrackerService
import com.cryptocurrencytracker.demo.helper.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by gokhan on 2/7/21.
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) = retrofit.create(CryptoCurrencyTrackerService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient = OkHttpClient().newBuilder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        callTimeout(45, TimeUnit.SECONDS)
        addInterceptor { chain ->
            val chainRequest = chain.request().newBuilder().build()
            chain.proceed(chainRequest)
        }
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()
}
