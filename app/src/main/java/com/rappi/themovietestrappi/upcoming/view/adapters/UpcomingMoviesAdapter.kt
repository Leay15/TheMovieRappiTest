package com.rappi.themovietestrappi.upcoming.view.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.core.modules.GlideApp
import com.rappi.themovietestrappi.movieDetail.view.MovieDetailActivity
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.MovieResponse
import kotlinx.android.synthetic.main.movie_item.view.*

class UpcomingMoviesAdapter(private val context: Context) :
    RecyclerView.Adapter<UpcomingMoviesAdapter.ViewHolder>() {

    private val items: MutableList<MovieResponse> = mutableListOf()

    fun addItems(newItems: List<MovieResponse>) {
        items.addAll(newItems)
        this.notifyDataSetChanged()
    }

    fun showFilterItems(filterItems: List<MovieResponse>) {
        items.clear()
        items.addAll(filterItems)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val path = "${DataConfiguration.BASE_IMAGES_URL}${items[position].posterPath}"


        GlideApp.with(context)
            .load(Uri.parse(path))
            .centerCrop()
            .placeholder(R.drawable.ic_image_black_24dp)
            .into(holder.imageViewPoster)

        holder.titleTextView.text = items[position].title
        holder.popularityTextView.text = "${items[position].popularity}"

        holder.movieItemContainer.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("movie_id", items[position].id)
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieItemContainer = itemView.movie_item_container!!
        val imageViewPoster = itemView.movie_item_image_view!!
        val titleTextView = itemView.movie_item_title_text_view!!
        val popularityTextView = itemView.movie_item_popularity_text_view!!

    }

}