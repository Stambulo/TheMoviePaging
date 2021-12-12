package com.stambulo.themovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stambulo.themovie.databinding.ItemVideoBinding
import com.stambulo.themovie.model.entity.VideoItem

class MoviesAdapter(private var onListItemClickListener: OnListItemClickListener) :
    PagingDataAdapter<VideoItem, MoviesAdapter.ViewHolder>(DataDiffCallback) {

    private lateinit var bindingItem: ItemVideoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        bindingItem = ItemVideoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VideoItem?) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                data?.let {
                    with(binding) {
                        root.setOnClickListener { onListItemClickListener.onItemClick(data) }
                        title.text = data.original_title
                        date.text = data.release_date
                        Glide.with(binding.ivImage.context)
                            .load("https://image.tmdb.org/t/p/w500/" + data.poster_path.toString())
                            .into(binding.ivImage)
                    }
                }
            }
        }
    }

    private object DataDiffCallback : DiffUtil.ItemCallback<VideoItem>() {
        override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

interface OnListItemClickListener { fun onItemClick(data: VideoItem) }
