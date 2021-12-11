package com.stambulo.themovie.model.api

import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.model.entity.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie?api_key=274f828ad283bd634ef4fc1ee4af255f")
    suspend fun getMovies(@Query("page") page: Int): Response<Discover>

    @GET("movie/{movie_id}?api_key=274f828ad283bd634ef4fc1ee4af255f")
    suspend fun getMovie(@Path("movie_id") movie_id: Int): Response<Video>
}
