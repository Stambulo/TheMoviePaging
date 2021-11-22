package com.stambulo.themovie.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stambulo.themovie.model.api.ApiHelper
import com.stambulo.themovie.model.repository.MoviesRepository
import com.stambulo.themovie.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class.java)
        return MainViewModel(MoviesRepository(apiHelper)) as T
    }
}
