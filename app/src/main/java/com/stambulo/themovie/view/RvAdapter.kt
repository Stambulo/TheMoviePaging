package com.stambulo.themovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stambulo.themovie.databinding.ItemVideoBinding
import com.stambulo.themovie.presenter.MovieListPresenter
import kotlinx.android.synthetic.main.item_video.view.*

class RvAdapter(val presenter: MovieListPresenter.RvPresenter) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemVideoBinding = ItemVideoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemVideoBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(itemVideoBinding: ItemVideoBinding): RecyclerView.ViewHolder(itemVideoBinding.root) {
        var pos = -1
        fun setId(id: Int) {
            itemView.tv_name.text = id.toString()
        }
        fun setSomeText(someText: String) {
            itemView.tv_release.text = someText
        }
    }
}
