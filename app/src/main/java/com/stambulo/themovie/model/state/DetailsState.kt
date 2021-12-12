package com.stambulo.themovie.model.state

sealed class DetailsState<out T> {
    data class Success<out T>(val data: T) : DetailsState<T>()
    data class Error(val error: Throwable) : DetailsState<Nothing>()
    data class Loading(val progress: Int?) : DetailsState<Nothing>()
}
