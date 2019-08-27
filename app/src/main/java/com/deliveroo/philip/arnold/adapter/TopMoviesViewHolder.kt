package com.deliveroo.philip.arnold.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deliveroo.philip.arnold.R
import com.deliveroo.philip.arnold.model.Result

class TopMoviesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val image: ImageView
    val title: TextView
    val description: TextView
    init {
        image = view.findViewById(R.id.image)
        title = view.findViewById(R.id.title)
        description = view.findViewById(R.id.description)
    }

    fun bind(post: Result) {
        title.text = post.title
        description.text = post.overview.trim()

        val url = String.format("%s%s", "https://image.tmdb.org/t/p/w500/", post.poster_path)
        Glide.with(image)
            .load(url)
            .into(image)
    }
}