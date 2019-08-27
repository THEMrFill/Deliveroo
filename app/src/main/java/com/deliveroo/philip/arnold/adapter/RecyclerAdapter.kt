package com.deliveroo.philip.arnold.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deliveroo.philip.arnold.R
import com.deliveroo.philip.arnold.model.TopRatedData

class RecyclerAdapter(val data: TopRatedData) : RecyclerView.Adapter<TopMoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        return TopMoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false))
    }

    override fun getItemCount(): Int = data.results.size

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        val item = data.results.get(position)
        holder.bind(item)
    }

    fun LoadData(newData: TopRatedData) {
        data.results.clear()
        data.results.addAll(newData.results)
        notifyDataSetChanged()
    }
}