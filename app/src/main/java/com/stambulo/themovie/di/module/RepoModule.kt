package com.stambulo.themovie.di.module

import com.stambulo.themovie.model.repository.IMovieRepository
import com.stambulo.themovie.model.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun provideMovies(api: IMovieRepository): IMovieRepository = MovieRepository(api)

    @Singleton
    @Provides
    fun provideMovie(api: IMovieRepository): IMovieRepository = MovieRepository(api)
}
