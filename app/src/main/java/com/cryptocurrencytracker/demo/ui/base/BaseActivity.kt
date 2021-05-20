package com.cryptocurrencytracker.demo.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.cryptocurrencytracker.demo.R
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by gokhan on 2/6/21.
 */

abstract class BaseActivity<VB: ViewBinding>: DaggerAppCompatActivity() {

    lateinit var activityBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        setApplicationTheme()
        super.onCreate(savedInstanceState)
        activityBinding = getActivityViewBinding()
        setContentView(activityBinding.root)
    }

    private fun setApplicationTheme() {
        when(AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                setTheme(R.style.Theme_CryptoCurrencyTracker_Dark)
            }
            else -> {
                setTheme(R.style.Theme_CryptoCurrencyTracker_Light)
            }
        }
    }

    abstract fun getActivityViewBinding(): VB
}
