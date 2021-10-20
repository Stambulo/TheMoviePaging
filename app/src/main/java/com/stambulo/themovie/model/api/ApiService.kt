package com.stambulo.themovie.model.api

import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.model.entity.Video
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
//    @GET("discover/movie")
//    fun getMovies(@Query("page") page: Int): Single<Discover>

    @GET("discover/movie?api_key=274f828ad283bd634ef4fc1ee4af255f&language=ru")
    fun getMovies(@Query("page") page: Int): Single<Discover>

    @GET("movie/{movie_id}?api_key=274f828ad283bd634ef4fc1ee4af255f&language=ru")
    fun getMovie(@Path("movie_id") movie_id: Int): Single<Video>
}
