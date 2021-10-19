package com.stambulo.themovie.model.api

import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.model.entity.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMoves(@Query("page") page: Int): Response<Discover>

    @GET("movie/{movie_id}")
    suspend fun getMove(@Path("movie_id") movie_id: Int): Response<Video>
}
