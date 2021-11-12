package com.stambulo.themovie.model.repository

import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.model.entity.Video
import retrofit2.Response

class MovieRepository(private val api: IMovieRepository): IMovieRepository {

    override suspend fun getMovies(page: Int): Response<Discover> {
        return api.getMovies(page)
    }

    override suspend fun getMovie(id: Int): Response<Video> {
        return api.getMovie(id)
    }
}
