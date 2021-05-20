@file:Suppress("UNCHECKED_CAST")

package com.cryptocurrencytracker.demo.helper

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Created by gokhan on 2/8/21.
 */

fun <T> LifecycleOwner.observe(
    liveData: LiveData<T>,
    action: (T?) -> Unit
) = liveData.observe(this, { action(liveData.value) })

fun <T> Context.getService(service: String) = getSystemService(service) as T

@Suppress("DEPRECATION")
fun Activity.getDisplayHeight(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        windowManager.currentWindowMetrics.bounds.height()
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.heightPixels
    }
}
