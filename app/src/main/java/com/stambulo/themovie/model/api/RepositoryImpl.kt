package com.stambulo.themovie.model.api

import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.model.entity.Video
import retrofit2.Response

class RepositoryImpl(private val apiService: ApiService) : Repository {
    override suspend fun getMovies(page: Int): Response<Discover> = apiService.getMovies(page)
    override suspend fun getMovie(id: Int): Response<Video> = apiService.getMovie(id)
}
