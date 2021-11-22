package com.stambulo.themovie.model.repository

import com.stambulo.themovie.model.api.ApiHelper

class MoviesRepository(private val apiHelper: ApiHelper) {
    suspend fun getMovies(page: Int) = apiHelper.getMovies(page)
}
