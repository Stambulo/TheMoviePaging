package com.stambulo.themovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stambulo.themovie.databinding.ItemLoadStateBinding

class MovieLoadStateAdapter(
    private val retry: () -> Unit,
) : LoadStateAdapter<MovieLoadStateAdapter.ItemViewHolder>() {

    private lateinit var bindingItem: ItemLoadStateBinding

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        bindingItem.btnRetry.isVisible = loadState is LoadState.Error
        bindingItem.btnRetry.setOnClickListener { retry.invoke() }
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        bindingItem =
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(bindingItem)
    }

    inner class ItemViewHolder(loadStateViewBinding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(loadStateViewBinding.root) {

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                bindingItem.tvErrorMessage.text = loadState.error.localizedMessage
                bindingItem.tvErrorMessage.isVisible = loadState !is LoadState.Loading
                bindingItem.btnRetry.isVisible = loadState !is LoadState.Loading
            }
            retry.invoke()
        }
    }
}
