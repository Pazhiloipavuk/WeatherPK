package com.example.weatherpk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherpk.R

class ListAdapter(var items : ArrayList<String>, val clickListener: (String) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
        //if (listener != null) {
        //    holder.setOnClickListener(View.OnClickListener { listener.onItemClick(item, it) })
        //}
    }
}