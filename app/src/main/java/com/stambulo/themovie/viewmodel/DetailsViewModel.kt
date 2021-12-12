package com.stambulo.themovie.viewmodel

import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import com.stambulo.themovie.model.api.Repository
import com.stambulo.themovie.model.api.RepositoryImpl
import com.stambulo.themovie.model.entity.Video
import com.stambulo.themovie.model.state.DetailsState
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private var _data = MutableLiveData<DetailsState<Video>>()
    var data: LiveData<DetailsState<Video>> = _data

    fun getDetails(id: Int) {
        _data.value = DetailsState.Loading(null)
        viewModelScope.launch {
            try {
                val data = repository.getMovie(id)
                _data.value = DetailsState.Success(data.body()!!)
            } catch (e: Exception) { _data.value = DetailsState.Error(e) }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val repository: RepositoryImpl) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(repository) as T
    }
}
