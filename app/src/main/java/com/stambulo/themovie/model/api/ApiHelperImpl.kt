package com.stambulo.themovie.model.api

import com.stambulo.themovie.model.entity.Discover
import retrofit2.Response

class ApiHelperImpl (private val apiService: ApiService): ApiHelper {
    override suspend fun getMovies(page: Int): Response<Discover> {
        return apiService.getMovies(page)
    }
}
