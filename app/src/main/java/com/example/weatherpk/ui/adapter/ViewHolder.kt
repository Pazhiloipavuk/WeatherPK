package com.example.weatherpk.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.recycler_item.view.*

class ViewHolder(private val view: View, var clickSubject: PublishSubject<String>) : RecyclerView.ViewHolder(view) {

    private val addressLeft = "https://storage.googleapis.com/multi-static-content/previews/artage-io-thumb-bec00a74bb6540dcf874b799fe19ae92.png"
    private val addressRight = "https://storage.googleapis.com/multi-static-content/previews/artage-io-thumb-74c5ba577e8e163d232a745208400deb.png"

    fun bind(item: String) {
        showCities(item)
        setOnClickListener(item)
    }

    private fun showCities(item: String) {
        Glide.with(view).load(addressLeft).into(itemView.vIvLeft)
        itemView.city.text = item
        Glide.with(view).load(addressRight).into(itemView.vIvRight)
    }

    private fun setOnClickListener(item: String) {
        itemView.setOnClickListener {
            clickSubject.onNext(item)
        }
    }
}