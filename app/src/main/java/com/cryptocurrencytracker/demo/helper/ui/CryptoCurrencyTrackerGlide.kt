package com.cryptocurrencytracker.demo.helper.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cryptocurrencytracker.demo.R

/**
 * Created by gokhan on 2/8/21.
 */
@BindingAdapter("loadImage")
fun ImageView.loadImage(uri: String) = Glide.with(this)
    .load(uri)
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .placeholder(R.color.black)
    .circleCrop()
    .error(R.drawable.ic_baseline_error_outline_40)
    .into(this)
