package com.cryptocurrencytracker.demo.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.cryptocurrencytracker.demo.App
import com.cryptocurrencytracker.demo.databinding.FragmentSettingsBinding
import com.cryptocurrencytracker.demo.ui.base.BaseFragment
import javax.inject.Inject

/**
 * Created by gokhan on 2/13/21.
 */

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    @Inject
    lateinit var app: App

    override fun getFragmentViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
    }

    private fun setView() {
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                fragmentBinding.switchMode.isChecked = true
            }
            else -> {
                fragmentBinding.switchMode.isChecked = false
            }
        }

        fragmentBinding.switchMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            activity?.recreate()
        }
    }
}
