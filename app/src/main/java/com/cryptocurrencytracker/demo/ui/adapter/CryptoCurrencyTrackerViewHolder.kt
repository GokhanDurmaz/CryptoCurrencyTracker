package com.cryptocurrencytracker.demo.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.cryptocurrencytracker.demo.R
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyData
import com.cryptocurrencytracker.demo.databinding.CoinItemBinding
import com.cryptocurrencytracker.demo.helper.ui.loadImage
import javax.inject.Inject

/**
 * Created by gokhan on 2/8/21.
 */

class CryptoCurrencyTrackerViewHolder @Inject constructor(
    private val binding: CoinItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currencyDataItem: CurrencyData) {
        binding.coinImage.loadImage(currencyDataItem.smallImage)
        binding.coinName.text = currencyDataItem.name
        binding.coinSymbol.text = currencyDataItem.symbol
        binding.lastUpdated.text = currencyDataItem.lastUpdated
        when {
            currencyDataItem.favorite -> {
                binding.coinFavorite.setImageResource(R.drawable.custom_favorite_button)
            }
            currencyDataItem.favorite.not() -> {
                binding.coinFavorite.setImageResource(R.drawable.custom_not_favorite_button)
            }
        }
    }
}
