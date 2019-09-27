package com.example.weatherpk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherpk.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ListAdapter(var items: ArrayList<String>) :
    RecyclerView.Adapter<ViewHolder>() {

    private val clickSubject = PublishSubject.create<String>()
    val clickEvent: Observable<String> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.recycler_item,
                    parent,
                    false
                ),
            clickSubject
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}