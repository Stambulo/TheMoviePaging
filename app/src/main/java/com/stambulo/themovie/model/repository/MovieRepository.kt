package com.stambulo.themovie.model.repository

import com.stambulo.themovie.model.api.ApiService
import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.model.entity.Video
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MovieRepository(private val api: ApiService) {
    fun getMovies(page: Int): Single<Discover>{
        return api.getMovies(page)
    }

    fun getMovie(id: Int): Single<Video> {
        return api.getMovie(id)
    }
}
