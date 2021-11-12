package com.stambulo.themovie.model.repository

import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.model.entity.Video
import retrofit2.Response

interface IMovieRepository {
    suspend fun getMovies(page: Int): Response<Discover>
    suspend fun getMovie(id: Int): Response<Video>
}
