package com.cryptocurrencytracker.demo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.cryptocurrencytracker.demo.data.db.currency.CurrencyData
import com.cryptocurrencytracker.demo.databinding.CoinItemBinding
import com.cryptocurrencytracker.demo.ui.CryptoCurrencyTrackerVm
import java.util.Locale
import javax.inject.Inject

/**
 * Created by gokhan on 2/6/21.
 */

class CryptoCurrencyTrackerAdapter @Inject constructor(
    private val cryptoCurrencyTrackerVm: CryptoCurrencyTrackerVm,
    private val coinSelected: (CurrencyData) -> Unit
) : RecyclerView.Adapter<CryptoCurrencyTrackerViewHolder>(), Filterable {

    private var currencyList = mutableListOf<CurrencyData>()

    fun setChanges(currencyDataItem: MutableList<CurrencyData>) {
        currencyList.clear()
        currencyList = currencyDataItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CryptoCurrencyTrackerViewHolder(
        binding = CoinItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CryptoCurrencyTrackerViewHolder, position: Int) {
        val currencyDataItem = currencyList[position]
        holder.bind(currencyDataItem)
        holder.itemView.setOnClickListener { coinSelected(currencyDataItem) }
    }

    override fun getItemCount() = currencyList.size

    override fun getFilter(): Filter = currencyFilter

    private val currencyFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResult = FilterResults()

            val clearText = constraint.toString().replace("\\s", "")
                .toLowerCase(Locale.getDefault())

            val filteredCurrencies = cryptoCurrencyTrackerVm.getCurrenciesAsNameOrSymbol(
                constraint = "%$clearText%"
            )

            filterResult.values = filteredCurrencies

            return filterResult
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val filteredCurrencyList = results?.values as MutableList<CurrencyData>?

            filteredCurrencyList?.also {
                setChanges(it)
            }
        }
    }
}
