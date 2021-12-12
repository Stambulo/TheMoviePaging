package com.stambulo.themovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stambulo.themovie.model.api.MoviePagingSource
import com.stambulo.themovie.model.api.Repository
import com.stambulo.themovie.model.api.RepositoryImpl
import com.stambulo.themovie.model.entity.VideoItem
import kotlinx.coroutines.flow.Flow

class VideosViewModel(repository: Repository) : ViewModel() {

    val movies: Flow<PagingData<VideoItem>> = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}

@Suppress("UNCHECKED_CAST")
class VideosViewModelFactory(private val repository: RepositoryImpl) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideosViewModel(repository) as T
    }
}
