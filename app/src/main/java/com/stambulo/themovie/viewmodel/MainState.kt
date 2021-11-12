package com.stambulo.themovie.viewmodel

import com.stambulo.themovie.model.entity.Discover
import retrofit2.Response

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Movies(val user: Response<Discover>) : MainState()
    data class Error(val error: String?) : MainState()
}
