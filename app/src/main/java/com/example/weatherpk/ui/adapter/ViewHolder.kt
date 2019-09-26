package com.example.weatherpk.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: String, clickListener: (String) -> Unit) {
        showCities(item)
        setOnClickListener(item, clickListener)
    }

    private fun showCities(item: String) {
        itemView.city.text = item
    }

    private fun setOnClickListener(item: String, clickListener: (String) -> Unit) {
        itemView.setOnClickListener { clickListener(item)}
    }
}