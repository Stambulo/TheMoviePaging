package com.stambulo.themovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stambulo.themovie.databinding.ItemVideoBinding
import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.model.entity.VideoItem

class MoviesAdapter(private var movies: ArrayList<VideoItem>):
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var itemMoviesBinding: ItemVideoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemMoviesBinding = ItemVideoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemMoviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemMoviesBinding: ItemVideoBinding) : RecyclerView.ViewHolder(itemMoviesBinding.root){
        fun bind(videoItem: VideoItem) {
            with(itemMoviesBinding){
                title.text = videoItem.original_title
                date.text = videoItem.release_date
                Glide.with(itemMoviesBinding.ivImage.context)
                    .load("https://image.tmdb.org/t/p/w500/" + videoItem.poster_path.toString())
                    .into(itemMoviesBinding.ivImage)
            }
        }
    }

    fun addData(movie: Discover) {
        movies = movie.results as ArrayList<VideoItem>
    }
}
