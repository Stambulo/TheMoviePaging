package com.stambulo.themovie.model.api

import com.stambulo.themovie.model.entity.Discover
import retrofit2.Response

interface ApiHelper {
    suspend fun getMovies(page: Int): Response<Discover>
}
